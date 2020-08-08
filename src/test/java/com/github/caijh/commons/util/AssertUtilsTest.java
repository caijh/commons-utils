package com.github.caijh.commons.util;

import org.junit.Test;

public class AssertUtilsTest {

    @Test
    public void test() throws Exception {
        Integer a = 1;
        AssertUtils.isTrue(a == 1, Exception.class);
    }

}
