package com.github.caijh.commons.util

import com.github.caijh.commons.util.UUID.get
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.IOException

/**
 * HttpClient工具类
 *
 * @author caijh
 */
object HttpClient {
    private val httpClient = OkHttpClient()
    private const val APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8"
    private val HEADERS_EMPTY: Headers? = null

    operator fun get(url: String): String? {
        return get(url, HEADERS_EMPTY)
    }

    /**
     * use http get method to get response content from url.
     *
     * @param url     url
     * @param headers headers
     * @return response content
     */
    operator fun get(url: String, headers: Headers?): String? {
        val builder = Request.Builder()
        if (headers != null) {
            builder.headers(headers)
        }
        val request: Request = builder.url(url).build()
        return getRespBody(request)
    }

    operator fun <T> get(url: String, convertor: RespBodyConvertor<T>): T {
        val respBody = get(url)
        return convertor.convert(respBody)
    }

    operator fun <T> get(url: String, headers: Headers?, convertor: RespBodyConvertor<T>): T {
        val respBody = get(url, headers)
        return convertor.convert(respBody)
    }

    @JvmStatic
    fun post(url: String, json: String?): String? {
        return post(url, null, APPLICATION_JSON_UTF8.toMediaTypeOrNull(), json)
    }

    @JvmStatic
    fun <T> post(url: String, json: String?, convertor: RespBodyConvertor<T>): T {
        val respBody = post(url, json)
        return convertor.convert(respBody)
    }

    @JvmStatic
    fun post(url: String, mediaType: MediaType?, content: String?): String? {
        return post(url, null, mediaType, content ?: "")
    }

    @JvmStatic
    fun <T> post(url: String, mediaType: MediaType?, content: String?, convertor: RespBodyConvertor<T>): T {
        val respBody = post(url, mediaType, content)
        return convertor.convert(respBody)
    }

    @JvmStatic
    fun <T> post(
        url: String,
        headers: Headers?,
        mediaType: MediaType?,
        content: String?,
        convertor: RespBodyConvertor<T>
    ): T {
        val respBody = post(url, headers, mediaType, content)
        return convertor.convert(respBody)
    }

    @JvmStatic
    fun post(url: String, headers: Headers?, mediaType: MediaType?, content: String?): String? {
        val builder = Request.Builder()
        if (headers != null) {
            builder.headers(headers)
        }
        val reqBody = content ?: ""
        val request: Request = builder.url(url)
            .post(reqBody.toRequestBody(mediaType))
            .build()
        return getRespBody(request)
    }

    private fun getRespBody(request: Request): String? {
        return try {
            val response = httpClient.newCall(request).execute()
            if (!response.isSuccessful) {
                throw HttpException(response.code, response.message)
            }
            response.body?.string()
        } catch (e: IOException) {
            throw HttpException(-1, e.message)
        }
    }

    /**
     * upload file to the url.
     *
     * @param url  url
     * @param file file to upload
     * @return ResponseBody
     */
    @JvmStatic
    fun upload(url: String, file: File): ResponseBody? {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", file.name,
                file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            )
            .build()
        val request: Request = Request.Builder()
            .header("Authorization", "Client-ID " + get())
            .url(url)
            .post(requestBody)
            .build()
        return try {
            val response = httpClient.newCall(request).execute()
            response.body
        } catch (e: Exception) {
            throw UploadException()
        }
    }

}
