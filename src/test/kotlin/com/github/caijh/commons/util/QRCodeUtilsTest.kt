package com.github.caijh.commons.util

import org.junit.After
import org.junit.Before
import org.junit.Test

class QRCodeUtilsTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun createQRCode() {
        val createQRCode = QRCodeUtils.createQRCode("hello", 100, 100)
        println(createQRCode)
    }

}
