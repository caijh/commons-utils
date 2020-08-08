package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CharUtilsTest {

    @Test
    public void test_addAll() {
        List<Character> target = new ArrayList<>();
        target.add('a');
        target.add('b');
        CharUtils.addAll(target, new char[]{'c', 'd'});

        Assert.assertEquals("abcd", new String(CharUtils.toCharArray(target)));
    }

}
