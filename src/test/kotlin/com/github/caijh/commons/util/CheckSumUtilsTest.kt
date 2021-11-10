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
        assertEquals("0bee89b07a248e27c83fc3d5951213c1", checkSum)

        checkSum = CheckSumUtils.checkSum(file)
        assertEquals("03cfd743661f07975fa2f1220c5194cbaff48451", checkSum)
    }

}
