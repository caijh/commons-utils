package com.github.caijh.commons.util;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author caijh
 * @since 2016-9-26
 */
public class Chars {

    private Chars() {
    }

    /**
     * 向@{code List<Character>}中添加字符数组中的字符数组.
     *
     * @param target List<Character> target
     * @param source char[] source
     */
    public static void addAll(List<Character> target, char[] source) {
        List<Character> collect = new String(source).chars().mapToObj(char.class::cast).collect(Collectors.toList());
        target.addAll(collect);
    }

    /**
     * 将List<Character>转化为字符数组.
     *
     * @param source List<Character>
     * @return char[]
     */
    public static char[] toCharArray(List<Character> source) {
        char[] target = new char[source.size()];
        for (int i = 0; i < source.size(); i++) {
            target[i] = source.get(i);
        }

        return target;
    }

}
