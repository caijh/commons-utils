package com.github.caijh.commons.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.caijh.commons.util.constants.Delimiters;

public class Strings {

    private Strings() {

    }

    /**
     * @param s     字符串
     * @param regex 分割符
     * @return list of string
     */
    public static List<String> toList(String s, String regex) {
        return Arrays.stream(s.split(regex)).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

    /**
     * @param s     字符串
     * @param regex 分割符
     * @param map   转化函数
     * @param <R>   list中元素的类型
     * @return list of R
     */
    public static <R> List<R> toList(String s, String regex, Function<String, R> map) {
        return Arrays.stream(s.split(regex)).filter(StringUtils::isNotBlank).map(map).collect(Collectors.toList());
    }

    public static List<String> toListByComma(String s) {
        return toList(s, Delimiters.COMMA);
    }

    public static List<Integer> toIntListByComma(String s) {
        return toList(s, Delimiters.COMMA, value -> Integer.valueOf(value.trim()));
    }

}
