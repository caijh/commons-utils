package com.github.caijh.commons.util

fun interface RespBodyConvertor<T> {
    fun convert(respBody: String?): T
}
