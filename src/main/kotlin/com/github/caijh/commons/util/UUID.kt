package com.github.caijh.commons.util

import java.util.*
import java.util.UUID

/**
 * UUID工具类
 */
object UUID {

    /**
     * 获取UUID
     *
     * @return UUID
     */
    @JvmStatic
    fun get(): String {
        return get(replaceDash = true, toUpperCase = true)
    }

    /**
     * 获取UUID
     *
     * @param replaceDash 是否替换dash为""
     * @return UUID
     */
    @JvmStatic
    fun get(replaceDash: Boolean): String {
        return get(true, replaceDash)
    }

    /**
     * 获取UUID
     *
     * @param replaceDash 是否替换dash为""
     * @param toUpperCase 是否转换为大写
     * @return UUID
     */
    @JvmStatic
    fun get(replaceDash: Boolean, toUpperCase: Boolean): String {
        var uuid = UUID.randomUUID().toString()
        if (replaceDash) {
            uuid = uuid.replace(Delimiters.DASH, "")
        }
        if (toUpperCase) {
            uuid = uuid.uppercase(Locale.getDefault())
        }
        return uuid
    }
}
