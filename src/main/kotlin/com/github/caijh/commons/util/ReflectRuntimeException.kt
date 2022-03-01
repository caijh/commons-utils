package com.github.caijh.commons.util

class ReflectRuntimeException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}
