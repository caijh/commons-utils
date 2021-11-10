package com.github.caijh.commons.util

class HttpException(val code: Int, message: String?) : RuntimeException(message)
