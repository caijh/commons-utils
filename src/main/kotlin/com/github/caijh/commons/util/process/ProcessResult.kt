package com.github.caijh.commons.util.process

/**
 * 调用命令的执行结果.
 */
class ProcessResult {
    var exitValue = -1
    var consoleString: String? = null
    val isSuccessful: Boolean
        get() = exitValue == 0
}
