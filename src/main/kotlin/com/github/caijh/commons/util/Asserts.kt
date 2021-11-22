package com.github.caijh.commons.util

import java.util.function.Supplier
/**
 * 断言工具类
 */
object Asserts {

    @JvmStatic
    fun <T : Throwable> notNull(obj: Any?, supplier: Supplier<T>) {
        if (obj == null) {
            throw supplier.get()
        }
    }

    @JvmStatic
    fun notNull(obj: Any?) {
        notNull(obj) { NullPointerException() }
    }

    @JvmStatic
    fun <T : Throwable> isTrue(expression: Boolean, supplier: Supplier<T>) {
        if (!expression) {
            throw supplier.get()
        }
    }
}
