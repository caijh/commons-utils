package com.github.caijh.commons.util;

import org.junit.Assert;
import org.junit.Test;

public class RandomUtilsTest {

    @Test
    public void random() {

        String randomStr = RandomUtils
            .random(6, new RandomUtils.Alphabet[]{
                RandomUtils.Alphabet.NUMBERS,
                RandomUtils.Alphabet.LOWERCASE_LETTERS,
                RandomUtils.Alphabet.UPPERCASE_LETTERS
            });

        System.out.println(randomStr);
        Assert.assertEquals(6, randomStr.length());

    }

}
