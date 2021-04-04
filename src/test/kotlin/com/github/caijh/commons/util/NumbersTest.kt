package com.github.caijh.commons.util

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class NumbersTest {
    @Test
    fun getRate() {
        val b1 = BigDecimal.valueOf(100)
        val b2 = BigDecimal.valueOf(90)
        val rate = Numbers.getRate(b1, b2)
        Assert.assertEquals(11.0F, rate)
    }
}
