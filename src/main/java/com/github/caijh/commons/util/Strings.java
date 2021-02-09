package com.github.caijh.commons.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.caijh.commons.util.constants.Delimiters;
import org.apache.commons.lang3.StringUtils;


public class Strings extends org.apache.commons.lang3.StringUtils {

    private Strings() {

    }

    public static int[] toIntArray(String s, String regex) {
        if (StringUtils.isBlank(s)) {
            return new int[]{};
        }

        return Arrays.stream(s.split(regex)).filter(Strings::isNotBlank).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * @param s     字符串
     * @param regex 分割符
     * @return list of string
     */
    public static List<String> toList(String s, String regex) {
        if (Objects.isNull(s)) {
            return Collections.emptyList();
        }
        return Arrays.stream(s.split(regex)).filter(Strings::isNotBlank).collect(Collectors.toList());
    }

    /**
     * @param s      字符串
     * @param regex  分割符
     * @param mapper 转化函数
     * @param <R>    list中元素的类型
     * @return list of R
     */
    public static <R> List<R> toList(String s, String regex, Function<String, R> mapper) {
        if (Objects.isNull(s)) {
            return Collections.emptyList();
        }
        return Arrays.stream(s.split(regex)).filter(Strings::isNotBlank).map(mapper).collect(Collectors.toList());
    }

    public static List<String> toListByComma(String s) {
        return toList(s, Delimiters.COMMA);
    }

    public static List<Integer> toIntListByComma(String s) {
        return toList(s, Delimiters.COMMA, Strings::toInt);
    }

    public static long toLong(String s) {
        return Long.parseLong(s.trim());
    }

    public static int toInt(String s) {
        return Integer.parseInt(s.trim());
    }

    public static String firstCharUpperCase(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        return stringBuilder.toString();
    }

    public static boolean isAnyBlank(String... s) {
        return Stream.of(s).anyMatch(Strings::isBlank);
    }

    /**
     * Supports rendering of Java numeric types float, double,
     * and BigDecimal in "default" format and in format that
     * avoids use of scientific notation.
     */
    private enum Format {
        DEFAULT {
            @Override
            public String fromFloat(final float floatValue) {
                return numberFormat.format(floatValue);
            }

            @Override
            public String fromDouble(final double doubleValue) {
                return numberFormat.format(doubleValue);
            }

            @Override
            public String fromBigDecimal(final BigDecimal bigDecimalValue) {
                return bigDecimalValue.toPlainString();
            }
        };

        private static final NumberFormat numberFormat = NumberFormat.getInstance();

        static {
            numberFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
            numberFormat.setGroupingUsed(false);
        }

        public abstract String fromFloat(final float floatValue);

        public abstract String fromDouble(final double doubleValue);

        public abstract String fromBigDecimal(final BigDecimal bigDecimalValue);
    }

    public static String toString(Object object) {
        if (object instanceof Date) {
            return DateUtils.format((Date) object);
        }
        if (object instanceof Number) {
            if (object instanceof Float) {
                return Format.DEFAULT.fromFloat((Float) object);
            }
            if (object instanceof Double) {
                return Format.DEFAULT.fromDouble((Double) object);
            }
            if (object instanceof BigDecimal) {
                return Format.DEFAULT.fromBigDecimal((BigDecimal) object);
            }
        }
        return object.toString();
    }

}
