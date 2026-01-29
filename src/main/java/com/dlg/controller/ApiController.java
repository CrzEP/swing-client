package com.dlg.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlg.util.HttpUtil;

public class ApiController {

    public static String getProductInfo(){
        String url = "";
        String param = "";
        JSONObject paramJson = JSONObject.parseObject(param);
        JSONObject jsonObject = HttpUtil.post(url, paramJson);
        return jsonObject.toJSONString();
    }

}
