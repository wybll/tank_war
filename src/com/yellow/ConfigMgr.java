package com.yellow;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description 配置加载类（单例）
 * @Author backen
 * @Date 2021/2/20 16:25
 */
public class ConfigMgr {

    private static  Properties property = new Properties();
    //单例模式步骤一
    private static  final ConfigMgr configMgr = new  ConfigMgr();

    static {
        try {
            property.load(ConfigMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //单例模式步骤二
    private ConfigMgr(){}

    //单例模式步骤三
    public static ConfigMgr getInstance(){
        return configMgr;
    }


    public static String get(String key){
        if(property == null)return "null";
        return (String) property.get(key);
    }


}
