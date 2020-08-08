package com.github.caijh.commons.util;

import org.junit.Test;

public class HashMapTest {

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= (1 << 30)) ? (1 << 30) : n + 1;
    }

    @Test
    public void test() {
        System.out.println(1 << 30);
        System.out.println(tableSizeFor(32));
    }

}
