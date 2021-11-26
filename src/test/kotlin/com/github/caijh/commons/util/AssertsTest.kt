package com.github.caijh.commons.util

import junit.framework.TestCase
import org.junit.Test

class AssertsTest : TestCase() {

    @Test
    fun testNotNull() {
        val a = 1
        Asserts.notNull(a)
        Asserts.notNull(a) { NullPointerException() }
        Asserts.isTrue(true).orElseThrow(NullPointerException())
    }

}
