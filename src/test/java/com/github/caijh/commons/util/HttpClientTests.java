package com.github.caijh.commons.util;

import org.junit.Assert;
import org.junit.Test;

public class HttpClientTests {

    @Test
    public void get() {
        String url = "http://www.baidu.com";
        Assert.assertNotNull(HttpClient.INSTANCE.get(url));
    }

}
