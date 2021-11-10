package com.github.caijh.commons.util

import java.security.*
import javax.crypto.*
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Aes加密工具.
 */
class AesUtils private constructor() {

    class AesKey(val key: ByteArray, val iv: ByteArray)

    companion object {
        private const val KEY_ALGORITHM = "AES"
        private const val CIPHER_ALGORITHM = "AES/GCM/NoPadding"

        @JvmStatic
        @Throws(NoSuchAlgorithmException::class)
        fun initAesKey(): AesKey {
            val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM)
            keyGenerator.init(256)
            val secretKey = keyGenerator.generateKey()
            val iv = ByteArray(16)
            SecureRandom.getInstance("SHA1PRNG").nextBytes(iv)
            return AesKey(secretKey.encoded, iv)
        }

        private fun toKey(key: ByteArray): Key {
            return SecretKeySpec(key, KEY_ALGORITHM)
        }

        @JvmStatic
        @Throws(
            NoSuchPaddingException::class,
            NoSuchAlgorithmException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class,
            InvalidAlgorithmParameterException::class
        )
        fun encrypt(data: ByteArray?, key: AesKey): ByteArray {
            val k = toKey(key.key)
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            val gcmParameterSpec = GCMParameterSpec(128, key.iv)
            cipher.init(Cipher.ENCRYPT_MODE, k, gcmParameterSpec)
            return cipher.doFinal(data)
        }

        @JvmStatic
        @Throws(
            NoSuchPaddingException::class,
            NoSuchAlgorithmException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class,
            InvalidAlgorithmParameterException::class
        )
        fun decrypt(data: ByteArray?, aesKey: AesKey): ByteArray {
            val k = toKey(aesKey.key)
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            val gcmParameterSpec = GCMParameterSpec(128, aesKey.iv)
            cipher.init(Cipher.DECRYPT_MODE, k, gcmParameterSpec)
            return cipher.doFinal(data)
        }
    }

    init {
        throw AssertionError("no instance for you")
    }
}
