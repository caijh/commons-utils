package com.github.caijh.commons.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Strings {

    private Strings() {

    }

    public static List<String> toList(String s, String regex) {
        return Arrays.stream(s.split(regex)).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

}
