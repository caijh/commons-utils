package com.github.caijh.commons.util.reflection.invoker

interface Invoker {
    fun invoke(obj: Any, vararg args: Any): Any
}
