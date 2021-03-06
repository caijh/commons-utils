package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CharsTest {

    @Test
    public void test_addAll() {
        List<Character> target = new ArrayList<>();
        target.add('a');
        target.add('b');
        Chars.addAll(target, new char[]{'c', 'd'});

        Assert.assertEquals("abcd", new String(Chars.toCharArray(target)));
    }

}
