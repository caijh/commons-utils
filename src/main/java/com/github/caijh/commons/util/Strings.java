package com.github.caijh.commons.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.caijh.commons.util.constants.Delimiters;
import org.apache.commons.lang3.StringUtils;

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

    public static List<String> toListByComma(String s) {
        return toList(s, Delimiters.COMMA);
    }

}
