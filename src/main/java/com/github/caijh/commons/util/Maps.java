package com.github.caijh.commons.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Maps {

    private Maps() {

    }

    /**
     * 将Object对象转化为Map对象.
     *
     * @param obj object
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(obj), Map.class);
    }

    /**
     * 将json转化为map对象.
     *
     * @param json json
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json) {
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }

    /**
     * @param map   map
     * @param clazz target object class
     * @param <T>   type parameter
     * @return the object of T
     */
    public static <T> T toObject(Map<?, Object> map, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * @param map1 map 1
     * @param map2 map 2
     * @param <K>  the type of key
     * @param <V>  the type of value
     * @return merge map, same key is replaced by next map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>();
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

}
