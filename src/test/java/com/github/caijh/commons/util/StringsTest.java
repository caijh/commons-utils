package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StringsTest {

    @Test
    public void toList() {
        final List<String> strings = Strings.toList("a,b,c", ",");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue(strings.containsAll(list));
    }

}
