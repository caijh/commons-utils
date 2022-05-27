package com.github.caijh.commons.util

class SerializedLambdaResolveException : RuntimeException {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
}