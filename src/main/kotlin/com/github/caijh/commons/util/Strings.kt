package com.github.caijh.commons.util

import com.github.caijh.commons.util.Collections.emptyList
import com.github.caijh.commons.util.DateUtils.Companion.format
import org.apache.commons.lang3.StringUtils
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import java.util.function.Function
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

object Strings {

    /**
     * 将字符串转换为int array.
     * 如1,2,3,4,5, regex：,
     */
    @JvmStatic
    fun toIntArray(s: String, regex: String): IntArray {
        return if (StringUtils.isBlank(s)) {
            intArrayOf()
        } else Arrays.stream(s.split(regex.toRegex()).toTypedArray())
            .filter { cs: String -> StringUtils.isNotBlank(cs) && StringUtils.isNumeric(cs) }
            .mapToInt { num: String -> Integer.valueOf(num) }
            .toArray()
    }

    /**
     * @param s     字符串
     * @param regex 分割符
     * @return list of string
     */
    @JvmStatic
    fun toList(s: String, regex: String): List<String> {
        return if (Objects.isNull(s)) {
            emptyList()
        } else Arrays.stream(s.split(regex.toRegex()).toTypedArray())
            .filter { cs: String? -> StringUtils.isNotBlank(cs) }
            .collect(Collectors.toList())
    }

    /**
     * @param s      字符串
     * @param regex  分割符
     * @param mapper 转化函数
     * @param <R>    list中元素的类型
     * @return list of R
    </R> */
    @JvmStatic
    fun <R> toList(s: String, regex: String, mapper: Function<String, R>): List<R> {
        return if (Objects.isNull(s)) {
            emptyList()
        } else Arrays.stream(s.split(regex.toRegex()).toTypedArray())
            .filter { cs: String? -> StringUtils.isNotBlank(cs) }
            .map(mapper).collect(Collectors.toList())
    }

    @JvmStatic
    fun toListByComma(s: String): List<String> {
        return toList(s, Delimiters.COMMA)
    }

    @JvmStatic
    fun toIntListByComma(s: String): List<Int> {
        return toList(s, Delimiters.COMMA) { obj: String -> toInt(obj) }
    }

    @JvmStatic
    fun toLong(s: String): Long {
        return s.trim { it <= ' ' }.toLong()
    }

    @JvmStatic
    fun isInt(s: String): Boolean {
        val pattern = Pattern.compile("^[1-9][0-9]*$")
        val matcher = pattern.matcher(s)
        return matcher.matches()
    }

    @JvmStatic
    fun toInt(s: String): Int {
        return s.trim { it <= ' ' }.toInt()
    }

    @JvmStatic
    fun firstCharUpperCase(str: String): String {
        require(!StringUtils.isBlank(str))
        val stringBuilder = StringBuilder(str)
        stringBuilder.setCharAt(0, Character.toUpperCase(str[0]))
        return stringBuilder.toString()
    }

    @JvmStatic
    fun isAnyBlank(vararg s: String?): Boolean {
        return Stream.of(*s).anyMatch { cs: String? -> StringUtils.isBlank(cs) }
    }

    @JvmStatic
    fun isBlank(s: String?): Boolean {
        return StringUtils.isBlank(s)
    }

    @JvmStatic
    fun isNotBlank(s: String?): Boolean {
        return StringUtils.isNotBlank(s)
    }

    fun toString(obj: Any): String {
        if (obj is Date) {
            return format(obj)
        }
        if (obj is Number) {
            if (obj is Float) {
                return Format.DEFAULT.fromFloat(obj)
            }
            if (obj is Double) {
                return Format.DEFAULT.fromDouble(obj)
            }
            if (obj is BigDecimal) {
                return Format.DEFAULT.fromBigDecimal(obj)
            }
        }
        return obj.toString()
    }

    /**
     * Supports rendering of Java numeric types float, double,
     * and BigDecimal in "default" format and in format that
     * avoids use of scientific notation.
     */
    private enum class Format {
        DEFAULT {
            override fun fromFloat(floatValue: Float): String {
                return numberFormat.format(floatValue.toDouble())
            }

            override fun fromDouble(doubleValue: Double): String {
                return numberFormat.format(doubleValue)
            }

            override fun fromBigDecimal(bigDecimalValue: BigDecimal): String {
                return bigDecimalValue.toPlainString()
            }
        };

        companion object {
            private val numberFormat = NumberFormat.getInstance()

            init {
                numberFormat.maximumFractionDigits = Int.MAX_VALUE
                numberFormat.isGroupingUsed = false
            }
        }

        abstract fun fromFloat(floatValue: Float): String
        abstract fun fromDouble(doubleValue: Double): String
        abstract fun fromBigDecimal(bigDecimalValue: BigDecimal): String
    }
}
