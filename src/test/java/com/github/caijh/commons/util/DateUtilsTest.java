package com.github.caijh.commons.util;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void getDate() {
        Date date = DateUtils.now();
        System.out.println(date);
    }

    @Test
    public void getCurrentUnixTimestamp() {
        long currentUnixTimestamp = DateUtils.getCurrentUnixTimestamp();
        System.out.println(currentUnixTimestamp);
    }

    @Test
    public void getDayOfWeek() {
        System.out.println(DateUtils.getDayOfWeek(DateUtils.now()));
    }

    @Test
    public void format() {
        System.out.println(DateUtils.format(DateUtils.now()));
    }

    @Test
    public void format1() {
        System.out.println(DateUtils.format(DateUtils.now(), "yyyy-MM-dd"));
    }

    @Test
    public void subtract() {
        Date date1 = DateUtils.now();
        Date date2 = DateUtils.now();
        System.out.println(DateUtils.subtract(date2, date1));
    }

    @Test
    public void test() throws ParseException {
        Date endDate = DateUtils.getDayLastTime(DateUtils.now());
        Date sendTime = DateUtils.parse("2018-04-04 09:23:47");
        Date confirmTime = DateUtils.rollDate(sendTime, 'D', 45);
        System.out.println(DateUtils.format(confirmTime));
        System.out.println(DateUtils.format(endDate));

        Date date = new Date(1536128057290L);
        System.out.println(DateUtils.format(date));

        Assert.assertTrue(confirmTime.before(endDate));
    }


}
