package com.github.caijh.commons.util

import okhttp3.Headers

object HttpUtils {
    fun <T> get(url: String, convertor: RespBodyConvertor<T>): T {
        val respBody = HttpClientUtils.get(url)
        return convertor.convert(respBody)
    }

    fun <T> get(url: String, headers: Headers, convertor: RespBodyConvertor<T>): T {
        val respBody = HttpClientUtils.get(url, headers)
        return convertor.convert(respBody)
    }

    fun <T> post(url: String, json: String, convertor: RespBodyConvertor<T>): T {
        val respBody = HttpClientUtils.post(url, json)
        return convertor.convert(respBody)
    }
}
