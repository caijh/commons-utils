package com.github.caijh.commons.util;

import org.junit.Ignore;
import org.junit.Test;

public class HttpClientUtilsTests {

    @Test
    @Ignore
    public void get() {
        String url = "http://www.baidu.com";
        System.out.println(HttpClientUtils.get(url));
    }

}
