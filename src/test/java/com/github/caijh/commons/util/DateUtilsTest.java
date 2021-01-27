package com.github.caijh.commons.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void test() {
        Date now = DateUtils.now();
        System.out.println(now);

        now = DateUtils.parse(DateUtils.format(now));

        String nowAsString = DateUtils.nowAsString();
        System.out.println(nowAsString);
        Date date = DateUtils.parse(nowAsString);
        Assert.assertEquals(now, date);

        System.out.println(DateUtils.currentTimestamp());

    }


}
