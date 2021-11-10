package com.github.caijh.commons.util.process

interface Command {
    val cmdName: String?
    val cmdArray: Array<String>
    fun exec(): ProcessResult? {
        val result = ProcessResult()
        try {
            val cmdArray = cmdArray
            val process = Runtime.getRuntime().exec(cmdArray)
            process.waitFor()
            val exitValue = process.exitValue()
            val consoleString = ProcessUtils.readConsoleString(process)
            result.exitValue = exitValue
            result.consoleString = consoleString
        } catch (e: Exception) {
            throw ProcessException("call process fail", e)
        }
        return result
    }
}
