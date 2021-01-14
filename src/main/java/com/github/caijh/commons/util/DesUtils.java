package com.github.caijh.commons.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

public class DesUtils implements SecretUtils {

    private static final DesUtils INSTANCE = new DesUtils();

    private DesUtils() {

    }

    public static DesUtils getInstance() {
        return INSTANCE;
    }

    @Override
    public String keyAlgorithm() {
        return "DES";
    }

    @Override
    public String cipherAlgorithm() {
        return "DES";
    }

    @Override
    public Key secretKey(String key) {
        byte[] result = new byte[8];
        byte[] keys = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < 8; i++) {
            if (i < keys.length) {
                result[i] = keys[i];
            } else {
                result[i] = 0x01;
            }
        }
        return new SecretKeySpec(result, keyAlgorithm());
    }

}
