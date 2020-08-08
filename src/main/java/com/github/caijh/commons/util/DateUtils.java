package com.github.caijh.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils.
 *
 * @author caijunhui
 * @since 2017/5/23
 */
public class DateUtils {

    private static final String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {

    }

    public static Date now() {
        return new Date();
    }

    public static long getCurrentUnixTimestamp() {
        return new Date().getTime() / 1000;
    }

    public static Date getDayLastTime(Date date) {
        if (null == date) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MILLISECOND, 0);
        return ca.getTime();
    }

    public static Date rollDate(Date curDate, char unit, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);

        switch (unit) {
            case 'M':
                c.add(Calendar.MINUTE, value);
                break;
            case 'H':
                c.add(Calendar.HOUR, value);
                break;
            case 'D':
                c.add(Calendar.DATE, value);
                break;
            default:
                break;
        }

        return c.getTime();
    }

    public static int getDayOfWeek(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfWeek().getValue();
    }

    public static String format(Date date) {
        return format(date, DATE_TIME_FORMAT_DEFAULT);
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String s) throws ParseException {
        return parse(s, DATE_TIME_FORMAT_DEFAULT);
    }

    public static Date parse(String s, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(s);
    }

    public static long subtract(Date minuendDate, Date subtrahendDate) {
        return minuendDate.getTime() - subtrahendDate.getTime();
    }


}
