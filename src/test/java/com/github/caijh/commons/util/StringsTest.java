package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringsTest {

    @Test
    public void toList() {
        final List<String> strings = Strings.toListByComma("a,b,c");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue(strings.containsAll(list));
    }

    @Test
    public void toIntList() {
        final List<Integer> strings = Strings.toIntListByComma("1,2,3");
        assertTrue(strings.contains(1));
    }

    @Test
    public void testToLong() {
        long a = Strings.toLong("123");
        assertEquals(123, a);
    }

    @Test
    public void test() {
        assertTrue(Strings.isNotBlank("a"));
    }

}
