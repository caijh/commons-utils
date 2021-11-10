package com.github.caijh.commons.util

import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.spec.SecretKeySpec

class DesUtils private constructor() : SecretUtils {
    override fun keyAlgorithm(): String {
        return "DES"
    }

    override fun cipherAlgorithm(): String {
        return "DES"
    }

    override fun secretKey(key: String): Key {
        val result = ByteArray(8)
        val keys = key.toByteArray(StandardCharsets.UTF_8)
        for (i in 0..7) {
            if (i < keys.size) {
                result[i] = keys[i]
            } else {
                result[i] = 0x01
            }
        }
        return SecretKeySpec(result, keyAlgorithm())
    }

    companion object {
        val instance = DesUtils()
    }
}
