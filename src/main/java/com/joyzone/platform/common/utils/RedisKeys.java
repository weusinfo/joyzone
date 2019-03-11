package com.joyzone.platform.common.utils;

/**
 * Redis所有Keys
 *
 * @author Louis.he
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
