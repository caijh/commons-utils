package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DesUtilsTest {

    @Test
    public void encrypt() {
        DesUtils desUtils = DesUtils.Companion.getInstance();

        String key = "aabbcc";
        String encrypt = desUtils.encrypt("1234caa", key);
        String decrypt = desUtils.decrypt(encrypt, key);

        assertEquals("1234caa", decrypt);

    }

}
