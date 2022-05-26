package com.github.caijh.commons.util.reflection.invoker

import java.lang.reflect.Field

class GetFieldInvoker(private val field: Field) : Invoker {

    override fun invoke(obj: Any, vararg args: Any): Any {
        return try {
            field.get(obj)
        } catch (e: Exception) {
            field.isAccessible = true
            field.get(obj)
        }
    }
}
