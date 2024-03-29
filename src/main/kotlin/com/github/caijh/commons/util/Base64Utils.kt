package com.github.caijh.commons.util

import java.util.*

/**
 * Base64编码工具类
 *
 * @author caijh
 * @since 1.0.0
 */
object Base64Utils {
    /**
     * BASE64解密
     *
     * @param str 字符串
     * @return byte[] base64 bytes
     */
    @JvmStatic
    fun decrypt(str: String?): ByteArray {
        return Base64.getDecoder().decode(str)
    }

    /**
     * BASE64加密
     *
     * @param bytes 字节数组
     * @return String base64 string
     */
    @JvmStatic
    fun encrypt(bytes: ByteArray?): String {
        return Base64.getEncoder().encodeToString(bytes)
    }

    /**
     * BASE64加密
     *
     * @param str 字符串
     * @return 加密后的字符串
     */
    @JvmStatic
    fun encrypt(str: String): String {
        return encrypt(str.toByteArray())
    }
}
