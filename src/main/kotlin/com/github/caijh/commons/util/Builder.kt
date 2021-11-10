package com.github.caijh.commons.util

import java.util.function.Consumer
import java.util.function.Supplier

class Builder<T> private constructor(private val supplier: Supplier<T>) {

    private val modifiers: MutableList<Consumer<T>> = ArrayList()

    fun <V> with(consumer: BuilderConsumer<T, V>, v: V): Builder<T> {
        val c = Consumer { t: T -> consumer.accept(t, v) }
        modifiers.add(c)
        return this
    }

    fun build(): T {
        val value = supplier.get()
        modifiers.forEach(Consumer { modifier: Consumer<T> -> modifier.accept(value) })
        modifiers.clear()
        return value
    }

    fun interface BuilderConsumer<T, V> {
        fun accept(t: T, v: V)
    }

    companion object {
        @JvmStatic
        fun <T> use(supplier: Supplier<T>): Builder<T> {
            return Builder(supplier)
        }
    }
}
