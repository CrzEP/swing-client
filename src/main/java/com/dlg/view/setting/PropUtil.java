package com.dlg.view.setting;

import com.dlg.util.Pt;
import lombok.Data;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Data
public class PropUtil {

    public final static String fontSize = "fontSize";

    private Map<String,Object> configMap = new HashMap<>();

    public void readConfig(){
        ResourceBundle bundle = ResourceBundle.getBundle("config", Locale.getDefault());
        // 读取属性
        String size = bundle.getString(fontSize);
        configMap.put(fontSize,Integer.valueOf(size));
        for(String key : configMap.keySet()){
            Pt.info(key + " : " + configMap.get(key));
        }
    }

    public Integer getInt(String key){
        return (Integer) configMap.get(key);
    }

    public String getString(String key){
        return String.valueOf(configMap.get(key));
    }

}
