package com.github.caijh.commons.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
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
            return Period.between(asLocalDate(d1), asLocalDate(d2)).days
        }

        @JvmStatic
        fun addSeconds(date: Date, seconds: Int): Date {
            return Date.from(
                asLocalDateTime(date).plusSeconds(seconds.toLong()).atZone(ZoneId.systemDefault()).toInstant()
            )
        }

        @JvmStatic
        fun addDays(date: Date, days: Int): Date {
            return Date.from(asLocalDateTime(date).plusDays(days.toLong()).atZone(ZoneId.systemDefault()).toInstant())
        }

        @JvmStatic
        fun asLocalDate(date: Date): LocalDate? {
            return Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDate()
        }

        @JvmStatic
        fun asLocalDateTime(date: Date): LocalDateTime {
            return Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDateTime()
        }

        @JvmStatic
        fun asDate(localDate: LocalDate): Date {
            return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        }

        @JvmStatic
        fun asDate(localDateTime: LocalDateTime): Date {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        }
    }

    init {
        throw InstanceNotSupportException()
    }
}
