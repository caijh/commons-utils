package com.github.caijh.commons.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTests {

    @Test
    public void testFormatFloat() {
        BigInteger bigInteger = new BigInteger("1000000000000000000");
        BigDecimal bigDecimal = new BigDecimal("0.0111111111111111111111111111111111");
        System.out.println(0.0111111111111111111111111111111111F);
        System.out.println(0.0111111111111111111111111111111111D);
        System.out.println(StringUtils.toString(bigInteger));
        System.out.println(StringUtils.toString(bigDecimal));
    }

    @Test
    public void testSplit() {
        String str = "公安警察/治安警察";
        String[] typeCodeNames = str.split("/");
        Assert.assertEquals("治安警察", typeCodeNames[typeCodeNames.length - 1]);
    }

}
