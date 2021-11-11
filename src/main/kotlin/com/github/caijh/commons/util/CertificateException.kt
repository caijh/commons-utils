package com.github.caijh.commons.util

class CertificateException(message: String, cause: Throwable) : RuntimeException(message, cause) {
    constructor(message: String) : this(message, Throwable(message))
}
