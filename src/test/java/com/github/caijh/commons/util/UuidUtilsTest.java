package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class UuidUtilsTest {

    @Test
    public void uuid() {
        String uuid = UUID.INSTANCE.get();;
        System.out.println(uuid);
        assertFalse(uuid.contains("-"));
        uuid = UUID.INSTANCE.get(true, true);
        assertFalse(uuid.contains("-"));
        System.out.println(uuid);
    }

}
