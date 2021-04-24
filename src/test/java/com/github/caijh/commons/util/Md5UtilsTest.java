package com.github.caijh.commons.util;

import org.junit.Assert;
import org.junit.Test;

public class Md5UtilsTest {

    @Test
    public void test_md5() {
        String a = "123";

        Assert.assertEquals("202CB962AC59075B964B07152D234B70", MD5.INSTANCE.md5(a).toUpperCase());
    }

}
