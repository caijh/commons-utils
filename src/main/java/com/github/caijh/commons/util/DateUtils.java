package com.github.caijh.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import javax.annotation.Nonnull;

import com.github.caijh.commons.util.exception.DateException;
import com.github.caijh.commons.util.exception.InstanceNotSupportException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * DateUtils.
 *
 * @author caijunhui
 * @since 2017/5/23
 */
public class DateUtils {

    private static final String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
        throw new InstanceNotSupportException();
    }

    public static Date now() {
        return new Date();
    }

    public static Date now(String pattern) {
        return parse(nowAsString(pattern), pattern);
    }

    public static String nowAsString() {
        return format(now());
    }

    public static String nowAsString(String pattern) {
        return format(now(), pattern);
    }

    public static long currentTimestamp() {
        return Instant.now().getEpochSecond();
    }

    public static long getTimestamp(Date date) {
        return date.toInstant().getEpochSecond();
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

    public static int daysBetween(@Nonnull Date d1, @Nonnull Date d2) {
        return Days.daysBetween(LocalDate.fromDateFields(d1), LocalDate.fromDateFields(d2)).getDays();
    }

    public static Date addSeconds(Date date, int seconds) {
        return LocalDateTime.fromDateFields(date).plusSeconds(seconds).toDate();
    }

    public static Date addDays(Date date, int days) {
        return LocalDateTime.fromDateFields(date).plusDays(days).toDate();
    }


}
