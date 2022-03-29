package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UrlInfoTest {

    @Test
    public void from() {
        String url = "http://user:123@127.0.0.1:8080/api/user.html#a?id=1&name=caijh";
        UrlInfo urlInfo = UrlInfo.from(url);
        assertNotNull(urlInfo);
        assertEquals("user", urlInfo.getUser());
        assertEquals("123", urlInfo.getPassword());
        assertEquals("http", urlInfo.getSchema());
        assertEquals("127.0.0.1", urlInfo.getHost());
        assertEquals("8080", urlInfo.getPort());
        System.out.println(urlInfo.getPath());
    }

}
