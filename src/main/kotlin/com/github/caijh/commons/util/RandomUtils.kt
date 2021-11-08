package com.github.caijh.commons.util

import java.security.SecureRandom

/**
 * @author caijunhui
 * @since 2016-9-26.
 */
object RandomUtils {
    private val LOWERCASE_LETTERS_ALPHABET = charArrayOf(
        'a', 'b', 'c',
        'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    )
    private val NUMBERS_ALPHABET = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val SYMBOLS_ALPHABET = charArrayOf(
        '!', '\"', '#', '$', '%',
        '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<',
        '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'
    )
    private val UPPERCASE_LETTERS_ALPHABET = charArrayOf(
        'A', 'B', 'C',
        'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    )
    private val rand = SecureRandom()

    @JvmStatic
    fun random(length: Int, alphabets: Array<Alphabet>): String {
        val random = CharArray(length)
        val list: MutableList<Char> = ArrayList()
        for (alphabet in alphabets) {
            Chars.addAll(list, getUseAlphabet(alphabet))
        }
        val source = Chars.toCharArray(list)
        for (i in 0 until length) {
            random[i] = source[rand.nextInt(source.size)]
        }
        return String(random)
    }

    private fun getUseAlphabet(alphabet: Alphabet): CharArray {
        val chars: CharArray = when (alphabet) {
            Alphabet.LOWERCASE_LETTERS -> LOWERCASE_LETTERS_ALPHABET
            Alphabet.NUMBERS -> NUMBERS_ALPHABET
            Alphabet.SYMBOLS -> SYMBOLS_ALPHABET
            Alphabet.UPPERCASE_LETTERS -> UPPERCASE_LETTERS_ALPHABET
        }
        return chars
    }

    enum class Alphabet {
        LOWERCASE_LETTERS, NUMBERS, SYMBOLS, UPPERCASE_LETTERS
    }
}
