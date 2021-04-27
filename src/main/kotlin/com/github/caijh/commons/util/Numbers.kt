package com.github.caijh.commons.util

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.Float.Companion.NaN

object Numbers {
    fun getRate(b1: BigDecimal, b2: BigDecimal, scale: Int = 2): Float {
        //计算差值
        if (b2 == b1) {
            //数值一样，说明没有增长
            return BigDecimal.ZERO.toFloat()
        }
        return if (b2 == BigDecimal.ZERO) {
            NaN
        } else {
            b1.subtract(b2).divide(b2, scale, RoundingMode.HALF_UP).toFloat() * 100
        }
    }

    fun getRate(b1: Int, b2: Int, scale: Int = 2): Float {
        return getRate(BigDecimal(b1.toString()), BigDecimal(b2), scale)
    }

    fun getPercent(n1: Int, n2: Int, scale: Int = 2): Int {
        if (n2 == 0) {
            return 100
        }

        return BigDecimal(n1.toString())
            .divide(BigDecimal(n2.toString()), scale, RoundingMode.HALF_UP).multiply(BigDecimal(100)).intValueExact()
    }
}
