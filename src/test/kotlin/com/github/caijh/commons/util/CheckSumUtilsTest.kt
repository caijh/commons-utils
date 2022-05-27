package com.github.caijh.commons.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.security.MessageDigest

class CheckSumUtilsTest {

    @Test
    fun checkSum() {
        val file = File("src/test/resources/test.txt")
        var checkSum = CheckSumUtils.checkSum(file, MessageDigest.getInstance("MD5"))
        assertEquals("C13B6AFECF97EA6B38D21A8F5167FA1E", checkSum.uppercase())

        checkSum = CheckSumUtils.checkSum(file)
        assertEquals("34D4150ADC3347F1DD8CE19FDF65B74D971AB602", checkSum.uppercase())
    }

}
