package com.joyzone.platform.common.utils;


import java.util.HashMap;


/**
 * Map工具类
 *
 * @author Louis.he
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
