package com.dlg.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    /**
     * 消息队列
     */
    private static final LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();

    /**
     * 连接池
     */
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            5,
            10,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    Pt.error("请求爆满");
                }
    });

    private static final Runnable MESSAGE_LISTENER = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(200);
                    if (!linkedHashMap.isEmpty()) {
                        onMessage(linkedHashMap);
                    }
                } catch (InterruptedException e) {
                    Pt.error(e.getMessage());
                }
            }
        }
    };

    static  {
        POOL_EXECUTOR.submit(MESSAGE_LISTENER);
    }

    private static void onMessage(LinkedHashMap<String, Object> linkedHashMap) {
        linkedHashMap.entrySet().stream().forEach(entry -> {
            Pt.info("" + entry.getValue());
        });
    }

    public static String synPost(String url, JSONObject paramJson) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Runnable callable = new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = post(url, paramJson);
                linkedHashMap.put(uuid,jsonObject   );
            }
        };
        POOL_EXECUTOR.submit(callable);
        return uuid;
    }

    /**
     * post请求
     *
     * @param url 请求值
     * @param json 请求参数
     * @return 响应参数
     */
    public static JSONObject post(String url, JSONObject json) {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.addRequestHeader("Cookie", "ci_session=rtj9e5p0u9ufa6bhkrltl6k54tv3mjfb");
        method.addRequestHeader("X-Service-Point-Access-Token", "48dba137639d2660fcd8ed454244f97a");
        //设置json格式传送
        method.addRequestHeader("Content-Type", "application/json");

        JSONObject jsonObject = null;
        try {
            int code = httpClient.executeMethod(method);
            if (code == 200) {
                String res = method.getResponseBodyAsString();
                Pt.info(res);
                jsonObject = JSONObject.parseObject(res);
            }
        } catch (IOException e) {
            Pt.error("请求失败，url " + url + "json "+json);
            Pt.error(e.getMessage());
        }
        return jsonObject;
    }

}
