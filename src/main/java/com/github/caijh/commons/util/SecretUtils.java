package com.github.caijh.commons.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.github.caijh.commons.util.exception.DecryptException;
import com.github.caijh.commons.util.exception.EncryptException;

public interface SecretUtils {

    String keyAlgorithm();

    String cipherAlgorithm();

    default Key secretKey(String key) {
        return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), keyAlgorithm());
    }

    default String encrypt(String str, String key) {
        try {
            Key secretKey = secretKey(key);
            Cipher cipher = Cipher.getInstance(cipherAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] data = str.getBytes(StandardCharsets.UTF_8);
            byte[] result = cipher.doFinal(data);
            return Base64Utils.encrypt(result);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    default String decrypt(String str, String key) {
        try {
            Key k = secretKey(key);
            Cipher cipher = Cipher.getInstance(cipherAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, k);

            byte[] data = Base64Utils.decrypt(str);
            byte[] result = cipher.doFinal(data);

            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecryptException(e);
        }
    }

}
