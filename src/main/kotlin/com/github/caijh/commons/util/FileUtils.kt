package com.github.caijh.commons.util

import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * FileUtils.
 *
 * @author caijunhui 2017/11/11
 */
object FileUtils {

    @Throws(IOException::class)
    fun createFile(pathname: String): File {
        val file = File(pathname)
        if (!file.exists()) {
            val ret = file.createNewFile()
            if (!ret) {
                throw IOException("create new file fail")
            }
        }
        return file
    }

    @Throws(IOException::class)
    fun package2Zip(zipFileName: String, zipEntryFiles: List<File>) {
        ZipOutputStream(FileOutputStream(zipFileName)).use { zout ->
            for (file in zipEntryFiles) {
                val zipEntry = ZipEntry(file.name)
                zout.putNextEntry(zipEntry)
                val fin = FileInputStream(file)
                val bytes = ByteArray(1024)
                while (fin.read(bytes) > -1) {
                    zout.write(bytes)
                }
                zout.closeEntry()
            }
        }
    }

    fun close(closeable: Closeable?) {
        closeable?.close()
    }

    fun delete(file: File): Boolean {
        return file.delete()
    }

    fun copy(src: File, target: File) {
        var `in`: FileChannel? = null
        var out: FileChannel? = null
        var inStream: FileInputStream? = null
        var outStream: FileOutputStream? = null
        try {
            inStream = FileInputStream(src)
            outStream = FileOutputStream(target)
            `in` = inStream.channel
            out = outStream.channel
            val buffer = ByteBuffer.allocate(4096)
            while (`in`.read(buffer) != -1) {
                buffer.flip()
                out.write(buffer)
                buffer.clear()
            }
        } catch (ignored: Exception) {
            // ignore
        } finally {
            close(`in`)
            close(out)
            close(inStream)
            close(outStream)
        }
    }
}
