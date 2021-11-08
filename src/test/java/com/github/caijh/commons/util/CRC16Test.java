package com.github.caijh.commons.util;

import org.junit.Test;

public class CRC16Test {

    @Test
    public void getCRC16() {
        String abc = "abc";
        int crc16 = CRC16.getValue(abc);
        System.out.println(crc16);
    }

}
