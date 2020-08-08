package com.github.caijh.commons.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Aes加密工具.
 */
public final class AesUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";

    private AesUtils() {
        throw new AssertionError("no instance for you");
    }

    public static AesKey initAesKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] iv = new byte[16];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(iv);

        return new AesKey(secretKey.getEncoded(), iv);
    }

    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, AesKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Key k = toKey(key.getKey());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, key.getIv());
        cipher.init(Cipher.ENCRYPT_MODE, k, gcmParameterSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, AesKey aesKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Key k = toKey(aesKey.getKey());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, aesKey.getIv());
        cipher.init(Cipher.DECRYPT_MODE, k, gcmParameterSpec);
        return cipher.doFinal(data);
    }

    public static class AesKey {

        private final byte[] key;
        private final byte[] iv;

        public AesKey(final byte[] key, final byte[] iv) {
            this.key = key;
            this.iv = iv;
        }

        public byte[] getKey() {
            return key;
        }

        public byte[] getIv() {
            return iv;
        }

    }

}
