package com.github.caijh.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Md5Utils {

    private Md5Utils() {

    }

    /**
     * 计算字符串的md5值.
     *
     * @param s 字符串
     * @return String
     * @throws NoSuchAlgorithmException if MD5 fail.
     */
    public static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes(UTF_8));

        byte[] byteData = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aByteData : byteData) {
            String hex = Integer.toHexString(0xff & aByteData);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
