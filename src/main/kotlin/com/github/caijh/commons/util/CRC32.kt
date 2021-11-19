package com.github.caijh.commons.util

import java.io.InputStream
import java.util.zip.CRC32
import java.util.zip.CheckedInputStream

/**
 * CRC32类
 *
 * @author caijh
 */
object CRC32 {
    /**
     * 计算文件流的CRC32
     * @param inputStream
     * @return
     */
    @JvmStatic
    fun calcCrc32Value(inputStream: InputStream): Int {
        var checkedInputStream: CheckedInputStream? = null
        val crc32 = CRC32()
        checkedInputStream = CheckedInputStream(inputStream, crc32)
        val buffer = ByteArray(1024 * 1024) //每次读取1M
        try {
            while (checkedInputStream.read(buffer, 0, buffer.size) != -1) {
                // do nothing
            }
        } finally {
            checkedInputStream.close()
        }
        val crc32Value = crc32.value
        return crc32Value.toInt()
    }

    /**
     * 计算字符串的CRC32
     * @param str 字符串
     * @return
     */
    @JvmStatic
    fun getValue(str: String): Int {
        return calcCrc32Value(str.toByteArray())
    }

    /**
     * 计算CRC32
     * @param buffer 计算crc32的byte数组
     * @return
     */
    @JvmStatic
    fun calcCrc32Value(buffer: ByteArray): Int {
        val crc32 = CRC32()
        crc32.update(buffer, 0, buffer.size)
        val crc32long = crc32.value
        return crc32long.toInt()
    }

}
