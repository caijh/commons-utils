package com.github.caijh.commons.util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * MD5加密工具类
 *
 * @author caijh
 */
object MD5 {
    /**
     * 计算字符串的md5值.
     *
     * @param s 字符串
     * @return md5 string
     */
    @JvmStatic
    fun get(s: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(s.toByteArray(StandardCharsets.UTF_8))
        val byteData = md.digest()
        val hexString = StringBuilder()
        for (aByteData in byteData) {
            val hex = Integer.toHexString(0xff and aByteData.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
}
