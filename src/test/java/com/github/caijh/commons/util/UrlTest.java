package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UrlTest {

    @Test
    public void from() {
        String url = "http://user:123@127.0.0.1:8080/api/user.html#a?id=1&name=caijh";
        Url from = Url.from(url);
        assertNotNull(from);
        assertEquals("user", from.getUser());
        assertEquals("123", from.getPassword());
        assertEquals("http", from.getSchema());
        assertEquals("127.0.0.1", from.getHost());
        assertEquals("8080", from.getPort());
    }

}
