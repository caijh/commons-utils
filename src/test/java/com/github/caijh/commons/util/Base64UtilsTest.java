package com.github.caijh.commons.util;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

public class Base64UtilsTest {

    @Test
    public void test() {
        Assert.assertEquals("MTIzNDU2", Base64Utils.encrypt("123456".getBytes(StandardCharsets.UTF_8)));

        Assert.assertEquals("950191", new String(Base64Utils.decrypt("OTUwMTkx"), StandardCharsets.UTF_8));
    }

}
