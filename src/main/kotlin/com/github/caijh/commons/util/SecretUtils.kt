package com.github.caijh.commons.util

import com.github.caijh.commons.util.Base64Utils.decrypt
import com.github.caijh.commons.util.Base64Utils.encrypt
import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

interface SecretUtils {
    fun keyAlgorithm(): String
    fun cipherAlgorithm(): String

    fun secretKey(key: String): Key {
        return SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), keyAlgorithm())
    }

    fun encrypt(str: String, key: String): String? {
        return try {
            val secretKey = secretKey(key)
            val cipher = Cipher.getInstance(cipherAlgorithm())
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val data = str.toByteArray(StandardCharsets.UTF_8)
            val result = cipher.doFinal(data)
            encrypt(result)
        } catch (e: Exception) {
            throw EncryptException(e)
        }
    }

    fun decrypt(str: String?, key: String): String? {
        return try {
            val k = secretKey(key)
            val cipher = Cipher.getInstance(cipherAlgorithm())
            cipher.init(Cipher.DECRYPT_MODE, k)
            val data = decrypt(str)
            val result = cipher.doFinal(data)
            String(result, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            throw DecryptException(e)
        }
    }
}
