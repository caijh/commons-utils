package com.github.caijh.commons.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caijh
 * @since 2016-9-26.
 */
public class RandomUtils {

    private static final char[] LOWERCASE_LETTERS_ALPHABET = {'a', 'b', 'c',
        'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] NUMBERS_ALPHABET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] SYMBOLS_ALPHABET = {'!', '\"', '#', '$', '%',
        '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<',
        '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~',};
    private static final char[] UPPERCASE_LETTERS_ALPHABET = {'A', 'B', 'C',
        'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final SecureRandom rand = new SecureRandom();

    private RandomUtils() {

    }

    public static String random(int length, Alphabet[] alphabets) {
        char[] random = new char[length];
        List<Character> list = new ArrayList<>();
        for (Alphabet alphabet : alphabets) {
            CharUtils.addAll(list, getUseAlphabet(alphabet));
        }
        char[] source = CharUtils.toCharArray(list);
        for (int i = 0; i < length; i++) {
            random[i] = source[rand.nextInt(source.length)];
        }
        return new String(random);
    }

    private static char[] getUseAlphabet(Alphabet alphabet) {
        char[] chars = null;
        switch (alphabet) {
            case LOWERCASE_LETTERS:
                chars = LOWERCASE_LETTERS_ALPHABET;
                break;
            case NUMBERS:
                chars = NUMBERS_ALPHABET;
                break;
            case SYMBOLS:
                chars = SYMBOLS_ALPHABET;
                break;
            case UPPERCASE_LETTERS:
                chars = UPPERCASE_LETTERS_ALPHABET;
                break;
            default:
                break;
        }

        return chars;
    }

    public enum Alphabet {
        LOWERCASE_LETTERS, NUMBERS, SYMBOLS, UPPERCASE_LETTERS
    }

}
