package com.dlg.util;

import com.dlg.handler.ExceptionHandler;
import com.dlg.view.compant.ToastDialog;

import java.util.LinkedList;

/**
 * 消息队列工具
 */
public class MqUtil {

    public static class MqMsg {

        private Topic topic;

        private String tag;

        private Object data;

    }

    public enum Topic {
        /**
         * 吐丝弹窗
         */
        TOAST;
    }

    private static final LinkedList<MqMsg> queue = new LinkedList<>();

    /**
     * 推送
     *
     * @param topic 主题
     * @param tag   标签
     * @param data  数据
     */
    public static void push(Topic topic, String tag, Object data) {
        MqMsg mqMsg = new MqMsg();
        mqMsg.topic = topic;
        mqMsg.tag = tag;
        mqMsg.data = data;
        queue.add(mqMsg);
    }

    /**
     * 推送吐司消息
     *
     * @param message 消息
     */
    public static void pushToast(String message) {
        push(Topic.TOAST,null,message);
    }

    /**
     * 启动
     */
    public static synchronized void start() {
        Runnable runnable = () -> {
            ExceptionHandler.addExHandler();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Pt.error(e.getMessage());
            }
            while (!queue.isEmpty()) {
                processMsg();
            }
        };
        ThreadPool.submit(runnable);
    }

    private static void processMsg() {
        MqMsg mqMsg = queue.poll();
        if (null == mqMsg) {
            return;
        }
        switch (mqMsg.topic) {
            case TOAST:
                GRun.update(() -> {
                    String msg = mqMsg.data.toString();
                    if (!ToastDialog.showMsg(msg)){
                        queue.addLast(mqMsg);
                    }
                });
                break;
            default:
                Pt.error("缺失消息消费者 ： " + mqMsg);
        }
    }

}
