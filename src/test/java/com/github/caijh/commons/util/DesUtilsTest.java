package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DesUtilsTest {

    @Test
    public void encrypt() {

        String key = "aabbcc";
        String encrypt = DesUtils.INSTANCE.encrypt("1234caa", key);
        String decrypt = DesUtils.INSTANCE.decrypt(encrypt, key);

        assertEquals("1234caa", decrypt);

    }

}
