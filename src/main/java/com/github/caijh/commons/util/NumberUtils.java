package com.github.caijh.commons.util;

import java.util.OptionalLong;

/**
 * NumberUtils.
 *
 * @author caijunhui on 2017/7/4
 */
public class NumberUtils {

    private NumberUtils() {

    }

    public static OptionalLong parse(String s) {
        try {
            return OptionalLong.of(Long.parseLong(s));
        } catch (NumberFormatException e) {
            return OptionalLong.empty();
        }
    }

}
