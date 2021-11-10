package com.github.caijh.commons.util.exception

class HttpException(val code: Int, message: String?) : RuntimeException(message)
