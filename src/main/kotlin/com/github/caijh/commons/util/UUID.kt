package com.github.caijh.commons.util

import com.github.caijh.commons.util.constants.Delimiters
import java.util.UUID

object UUID {
    fun get(): String {
        return get(true, true)
    }

    fun get(replaceDash: Boolean): String {
        return get(true, replaceDash)
    }

    fun get(replaceDash: Boolean, toUpperCase: Boolean): String {
        var uuid = UUID.randomUUID().toString()
        if (replaceDash) {
            uuid = uuid.replace(Delimiters.DASH, "")
        }
        if (toUpperCase) {
            uuid = uuid.toUpperCase()
        }
        return uuid
    }
}
