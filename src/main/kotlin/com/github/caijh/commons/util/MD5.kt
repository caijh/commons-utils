package com.github.caijh.commons.util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5 {
    /**
     * 计算字符串的md5值.
     *
     * @param s 字符串
     * @return md5 string
     * @throws NoSuchAlgorithmException if MD5 fail.
     */
    @Throws(NoSuchAlgorithmException::class)
    fun md5(s: String): String {
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
