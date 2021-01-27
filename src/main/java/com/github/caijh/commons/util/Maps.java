package com.github.caijh.commons.util;

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

}
