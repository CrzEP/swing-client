package com.dlg.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlg.util.HttpUtil;

public class ApiController {

    public static String getProductInfo(){
        String url = "https://app.servicepoints.nl/api/v2/get-products";
        String param = "{\n" +
                "    \"is_quotation_product\":2\n" +
                "}";
        JSONObject paramJson = JSONObject.parseObject(param);
        JSONObject jsonObject = HttpUtil.post(url, paramJson);
        return jsonObject.toJSONString();
    }

}
