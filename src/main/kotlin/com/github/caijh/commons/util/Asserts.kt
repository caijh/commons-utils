package com.github.caijh.commons.util

import java.util.function.Supplier

object Asserts {

    fun <T : Throwable> notNull(obj: Any?, supplier: Supplier<T>) {
        if (obj == null) {
            throw supplier.get()
        }
    }

    fun notNull(obj: Any?) {
        notNull(obj) { NullPointerException() }
    }

    fun <T : Throwable> isTrue(expression: Boolean, supplier: Supplier<T>) {
        if (!expression) {
            throw supplier.get()
        }
    }
}
