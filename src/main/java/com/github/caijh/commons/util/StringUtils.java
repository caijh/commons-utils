package com.github.caijh.commons.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.stream.Stream;

/**
 * String utils.
 *
 * @author caijunhui 2017/11/6
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {

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

    public static String firstCharUpperCase(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        return stringBuilder.toString();
    }

    public static boolean isAnyBlank(String... s) {
        return Stream.of(s).anyMatch(StringUtils::isBlank);
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

}
