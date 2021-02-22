package com.github.caijh.commons.util;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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

        System.out.println(DateUtils.nowAsString("yyyy-MM-dd"));

        System.out.println(DateUtils.currentTimestamp());

        System.out.println(DateUtils.getTimestamp(date));

        int days = DateUtils.daysBetween(now, date);

        Assert.assertEquals(0, days);

        Assert.assertEquals(1, DateUtils.daysBetween(date, LocalDate.fromDateFields(date).plusDays(1).toDate()));

        now = DateUtils.now("HH:mm:ss");
        System.out.println(DateUtils.format(now));

        System.out.println(DateUtils.format(LocalDateTime.now().minusYears(1).dayOfYear().withMinimumValue().withMillisOfDay(0).toDate()));
        System.out.println(DateUtils.format(LocalDateTime.now().minusYears(1).dayOfYear().withMaximumValue().withTime(23, 59, 59, 999).toDate()));

        System.out.println(DateUtils.format(LocalDateTime.now().minusMonths(1).dayOfMonth().withMinimumValue().withMillisOfDay(0).toDate()));
        System.out.println(DateUtils.format(LocalDateTime.now().minusMonths(1).dayOfMonth().withMaximumValue().withTime(23, 59, 59, 999).toDate()));

        System.out.println(DateUtils.format((LocalDateTime.now().dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate())));

        now = DateUtils.now();
        System.out.println(DateUtils.format(now));
        Date newDate = DateUtils.addSeconds(now, 600);
        System.out.println(DateUtils.format(newDate));

        now = DateUtils.now();
        System.out.println(DateUtils.format(now));
        System.out.println(DateUtils.format(DateUtils.addDays(now, 1)));

        now = DateUtils.now();
        newDate = DateUtils.addSeconds(now, 600);
        System.out.println(DateUtils.format(newDate));

        Date mDate = getMiddleDate(now, newDate);
        System.out.println(DateUtils.format(mDate));
    }

    private Date getMiddleDate(Date d1, Date d2) {
        return LocalDateTime.fromDateFields(d1).plusMillis((int) Math.abs(d2.getTime() - d1.getTime()) / 2).toDate();
    }

}
