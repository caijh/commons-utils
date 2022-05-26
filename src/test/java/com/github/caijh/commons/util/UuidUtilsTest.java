package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UuidUtilsTest {

    @Test
    public void uuid() {
        String uuid = UUID.get();
        System.out.println(uuid);
        assertTrue(uuid.contains("-"));
        uuid = UUID.get(true, true);
        assertFalse(uuid.contains("-"));
        System.out.println(uuid);
    }

}
