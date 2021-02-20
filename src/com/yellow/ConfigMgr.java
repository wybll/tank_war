package com.yellow;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description TODO
 * @Author backen
 * @Date 2021/2/20 16:25
 */
public class ConfigMgr {

    static  Properties property = new Properties();
    static {
        try {
            property.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ConfigMgr configMgr;

    private ConfigMgr(){

    }

    public static ConfigMgr getInstance(){
        if (configMgr == null) configMgr = new ConfigMgr();
        return configMgr;
    }


    public static String get(String key){
        if(property == null)return "null";
        return (String) property.get(key);
    }


}
