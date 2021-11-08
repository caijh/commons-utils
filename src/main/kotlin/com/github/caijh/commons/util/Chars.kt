package com.github.caijh.commons.util

/**
 * @author caijunhui
 * @since 2016-9-26
 */
object Chars {
    /**
     * 向@{code List<Character>}中添加字符数组中的字符数组.
     *
     * @param target List<Character> target
     * @param source char[] source
    </Character></Character> */
    @JvmStatic
    fun addAll(target: MutableList<Char>, source: CharArray) {
        target.addAll(source.toList())
    }

    /**
     * 将List<Character>转化为字符数组.
     *
     * @param source List<Character>
     * @return char[]
    </Character></Character> */
    @JvmStatic
    fun toCharArray(source: List<Char>): CharArray {
        val target = CharArray(source.size)
        for (i in source.indices) {
            target[i] = source[i]
        }
        return target
    }
}
