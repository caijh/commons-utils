package com.github.caijh.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.caijh.commons.util.exception.DateException;

/**
 * DateUtils.
 *
 * @author caijunhui
 * @since 2017/5/23
 */
public class DateUtils {

    private static final String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {}

    public static Date now() {
        return new Date();
    }

    public static String nowAsString() {
        return format(now());
    }

    public static long currentTimestamp() {
        return new Date().getTime() / 1000;
    }

    public static String format(Date date) {
        return format(date, DATE_TIME_FORMAT_DEFAULT);
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String s) {
        return parse(s, DATE_TIME_FORMAT_DEFAULT);
    }

    public static Date parse(String s, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(s);
        } catch (ParseException e) {
            throw new DateException(e);
        }
    }


}
