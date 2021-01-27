package com.github.caijh.commons.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Maps {

    private Maps() {

    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> from(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(obj), Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json) {
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }

}
