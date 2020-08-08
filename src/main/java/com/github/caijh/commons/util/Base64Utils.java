package com.github.caijh.commons.util;

import java.util.Base64;

public class Base64Utils {

    private Base64Utils() {

    }

    /**
     * BASE64解密
     *
     * @param str 字符串
     * @return byte[] base64 bytes
     */
    public static byte[] decrypt(String str) {
        return Base64.getDecoder().decode(str);
    }

    /**
     * BASE64加密
     *
     * @param bytes 字节数组
     * @return String base64 string
     */
    public static String encrypt(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}
