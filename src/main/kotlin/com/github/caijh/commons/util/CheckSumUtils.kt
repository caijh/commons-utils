package com.github.caijh.commons.util

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * @author [caiqizhe@gmail.com](caiqizhe@gmail.com)
 * @since 2016-11-17
 */
object CheckSumUtils {
    /**
     * 获取文件的sum值.
     *
     * @param file the file
     * @return the sum value string of file
     * @throws NoSuchAlgorithmException SHA1 algorithm not found
     * @throws IOException              if read file fail
     */
    @Throws(NoSuchAlgorithmException::class, IOException::class)
    fun checkSum(file: File, digest: MessageDigest = MessageDigest.getInstance("SHA1")): String {
        FileInputStream(file).use { fis ->
            val dataBytes = ByteArray(1024)
            var bytesRead: Int
            while (fis.read(dataBytes).also { bytesRead = it } != -1) {
                digest.update(dataBytes, 0, bytesRead)
            }
            val mdBytes = digest.digest()

            // convert the byte to hex format
            val sb = StringBuilder()
            for (mdByte in mdBytes) {
                sb.append(((mdByte.toInt() and 0xff) + 0x100).toString(16).substring(1))
            }
            return sb.toString()
        }
    }
}
