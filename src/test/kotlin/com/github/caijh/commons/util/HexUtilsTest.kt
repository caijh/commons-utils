package com.github.caijh.commons.util

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HexUtilsTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun byteArr2HexStr() {
        val byteArr2HexStr = HexUtils.byteArr2HexStr("hello world".encodeToByteArray())
        println(byteArr2HexStr)

        val hexStr2ByteArr = HexUtils.hexStr2ByteArr(byteArr2HexStr)

        Assert.assertEquals("hello world", hexStr2ByteArr?.let { String(it) })
    }

}
