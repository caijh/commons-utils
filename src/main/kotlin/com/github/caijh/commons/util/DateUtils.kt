package com.github.caijh.commons.util

import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

/**
 * DateUtils.
 *
 * @author caijunhui
 * @since 2017/5/23
 */
class DateUtils private constructor() {
    companion object {
        private const val DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss"

        @JvmStatic
        fun now(): Date {
            return Date()
        }

        @JvmStatic
        fun now(pattern: String): Date {
            return parse(nowAsString(pattern), pattern)
        }

        @JvmStatic
        fun nowAsString(): String {
            return format(now())
        }

        @JvmStatic
        fun nowAsString(pattern: String): String {
            return format(now(), pattern)
        }

        @JvmStatic
        fun currentTimestamp(): Long {
            return Instant.now().epochSecond
        }

        @JvmStatic
        fun getTimestamp(date: Date): Long {
            return date.toInstant().epochSecond
        }

        @JvmStatic
        @JvmOverloads
        fun format(date: Date?, pattern: String = DATE_TIME_FORMAT_DEFAULT): String {
            if (date == null) {
                return ""
            }
            return SimpleDateFormat(pattern).format(date)
        }

        @JvmStatic
        @JvmOverloads
        fun parse(s: String?, pattern: String = DATE_TIME_FORMAT_DEFAULT): Date {
            return try {
                SimpleDateFormat(pattern).parse(s)
            } catch (e: ParseException) {
                throw DateException(e)
            }
        }

        @JvmStatic
        fun daysBetween(d1: Date, d2: Date): Int {
            return Days.daysBetween(LocalDate.fromDateFields(d1), LocalDate.fromDateFields(d2)).days
        }

        @JvmStatic
        fun addSeconds(date: Date, seconds: Int): Date {
            return LocalDateTime.fromDateFields(date).plusSeconds(seconds).toDate()
        }

        @JvmStatic
        fun addDays(date: Date, days: Int): Date {
            return LocalDateTime.fromDateFields(date).plusDays(days).toDate()
        }
    }

    init {
        throw InstanceNotSupportException()
    }
}
