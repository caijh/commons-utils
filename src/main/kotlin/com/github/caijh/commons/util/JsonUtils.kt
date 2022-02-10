package com.github.caijh.commons.util

import com.alibaba.fastjson.JSONObject

object JsonUtils {

    @JvmStatic
    fun <T> toObject(json: String, clazz: Class<T>): T {
        return JSONObject.parseObject(json, clazz)
    }

    @JvmStatic
    fun toJSONString(obj: Any): String {
        return JSONObject.toJSONString(obj)
    }

}
