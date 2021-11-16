package com.github.caijh.commons.util

import org.apache.commons.lang3.StringUtils


object HexUtils {

    @JvmStatic
    fun byteArr2HexStr(buf: ByteArray): String {
        val sb = StringBuilder()
        for (i in buf.indices) {
            var hex = Integer.toHexString(buf[i].toInt() and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            sb.append(hex)
        }
        return sb.toString()
    }

    @JvmStatic
    fun hexStr2ByteArr(hexStr: String): ByteArray? {
        if (StringUtils.isBlank(hexStr)) {
            return null
        }
        val result = ByteArray(hexStr.length shr 1)
        for (i in result.indices) {
            val index = i * 2
            val j: Int = hexStr.substring(index, index + 2).toInt(16)
            result[i] = j.toByte()
        }
        return result
    }
}
