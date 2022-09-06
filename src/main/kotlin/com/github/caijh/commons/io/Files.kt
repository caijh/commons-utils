package com.github.caijh.commons.io

import com.github.caijh.commons.util.Delimiters
import com.github.caijh.commons.util.Strings
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path

object Files {
    @JvmStatic
    fun getFileExtension(name: String?): String {
        if (name == null) {
            return Strings.EMPTY_STRING
        }
        val index = name.lastIndexOf(Delimiters.DOT)
        return if (index < 0) {
            Strings.EMPTY_STRING
        } else name.substring(index + 1)
    }

    @JvmStatic
    fun write(inputStream: InputStream, outputStream: OutputStream) {
        try {
            BufferedInputStream(inputStream).use { bis ->
                val buff = ByteArray(1024)
                val os: OutputStream = outputStream
                var i: Int
                while (bis.read(buff).also { i = it } != -1) {
                    os.write(buff, 0, i)
                    os.flush()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    @JvmStatic
    fun getContentType(path: Path): String {
        return Files.probeContentType(path)
    }

    @JvmStatic
    fun createTempFile(prefix: String, suffix: String): File {
        return Files.createTempFile(prefix, suffix).toFile()
    }

}
