package com.github.caijh.commons.io

import com.github.caijh.commons.util.Delimiters

object Files {
    @JvmStatic
    fun getFileExtension(name: String): String {
        val index = name.lastIndexOf(Delimiters.DOT)
        return if (index < 0) {
            ""
        } else name.substring(index + 1)
    }
}