package com.github.caijh.commons.util;

import org.junit.Test;

public class CRC16UtilsTest {

    @Test
    public void getCRC16() {
        String abc = "abc";
        int crc16 = CRC16Utils.getCRC16(abc);
        System.out.println(crc16);
    }

}
