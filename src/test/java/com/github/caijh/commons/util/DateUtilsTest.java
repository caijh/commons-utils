package com.github.caijh.commons.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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

        System.out.println(DateUtils.nowAsString("yyyy-MM-dd"));

        System.out.println(DateUtils.currentTimestamp());

        System.out.println(DateUtils.getTimestamp(date));

        int days = DateUtils.daysBetween(now, date);

        Assert.assertEquals(0, days);

        Assert.assertEquals(1, DateUtils.daysBetween(date, DateUtils.addDays(date, 1)));

        now = DateUtils.now("HH:mm:ss");
        System.out.println(DateUtils.format(now));

        System.out.println(DateUtils.format(DateUtils.asDate(LocalDateTime.now().minusYears(1).with(TemporalAdjusters.firstDayOfYear()).withHour(0)
                                                                          .withMinute(0).withSecond(0).withNano(0))));
        System.out.println(DateUtils.format(DateUtils.asDate(LocalDateTime.now().minusYears(1).with(TemporalAdjusters.lastDayOfYear()).withHour(23)
                                                                          .withMinute(59).withSecond(59).withNano(999))));

        System.out.println(DateUtils.format(DateUtils.asDate(LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).withHour(0)
                                                                          .withMinute(0).withSecond(0).withNano(0))));
        System.out.println(DateUtils.format(DateUtils.asDate(LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).withHour(23)
                                                                          .withMinute(59).withSecond(59).withNano(999))));

        System.out.println(DateUtils.format(DateUtils.asDate(LocalDateTime.now().with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59)
                                                                          .withNano(999))));

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
        return DateUtils.asDate(DateUtils.asLocalDateTime(d1).plus((int) Math.abs(d2.getTime() - d1.getTime()) / 2, ChronoUnit.MILLIS));
    }

}
