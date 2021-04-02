package com.github.caijh.commons.util

object Images {
    fun appendImageSchema(base64Str: String): String {
        return "data:image/png;base64,${base64Str}"
    }
}
