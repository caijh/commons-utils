package com.github.caijh.commons.util.http

fun interface RespBodyConvertor<T> {
    fun convert(respBody: String?): T
}
