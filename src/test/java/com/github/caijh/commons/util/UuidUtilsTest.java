package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class UuidUtilsTest {

    @Test
    public void uuid() {
        final String uuid = UuidUtils.uuid();
        System.out.println(uuid);
        assertFalse(uuid.contains("-"));
    }

}
