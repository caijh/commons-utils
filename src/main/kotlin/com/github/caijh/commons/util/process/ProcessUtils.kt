package com.github.caijh.commons.util.process

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object ProcessUtils {
    @Throws(IOException::class)
    fun readConsoleString(process: Process): String {
        val console = StringBuilder()
        val bufferIn = BufferedReader(InputStreamReader(process.inputStream, StandardCharsets.UTF_8))
        val bufferedError = BufferedReader(InputStreamReader(process.errorStream, StandardCharsets.UTF_8))
        var line: String?
        while (bufferIn.readLine().also { line = it } != null || bufferedError.readLine().also { line = it } != null) {
            console.append(line).append('\n')
        }
        return console.toString()
    }
}
