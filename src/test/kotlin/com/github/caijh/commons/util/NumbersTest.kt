package com.github.caijh.commons.util

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class NumbersTest {
    @Test
    fun test() {
        val b1 = BigDecimal.valueOf(100)
        val b2 = BigDecimal.valueOf(90)
        val rate = Numbers.getRate(b1, b2)
        Assert.assertEquals(11.0F, rate)

        val i1 = 100
        val i2 = 90
        Assert.assertEquals(11.0F, Numbers.getRate(i1, i2))

        println(Numbers.getRate(1, 3))

        println(Numbers.getPercent(1, 100))
        println(Numbers.getPercent(1, 0))
        println(Numbers.getPercent(1, -1))
    }
}
