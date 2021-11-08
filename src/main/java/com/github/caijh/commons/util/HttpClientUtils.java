package com.github.caijh.commons.util;

import java.io.File;
import java.io.IOException;
import javax.annotation.Nonnull;

import com.github.caijh.commons.util.exception.HttpException;
import com.github.caijh.commons.util.exception.UploadException;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClientUtils {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
    private static final Headers HEADERS_EMPTY = null;

    private HttpClientUtils() {
    }

    public static String get(String url) {
        return get(url, HEADERS_EMPTY);
    }

    public static <T> T get(String url, @Nonnull RespBodyConvertor<T> convertor) {
        String respBody = get(url);
        return convertor.convert(respBody);
    }

    /**
     * use http get method to get response content from url.
     *
     * @param url     url
     * @param headers headers
     * @return response content
     */
    public static String get(String url, Headers headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        Request request = builder.url(url).build();
        return getRespBody(request);
    }

    public static <T> T get(String url, @Nonnull Headers headers, @Nonnull RespBodyConvertor<T> convertor) {
        String respBody = get(url, headers);
        return convertor.convert(respBody);
    }

    public static String post(String url, String json) {
        return post(url, null, MediaType.parse(APPLICATION_JSON_UTF8), json);
    }

    public static <T> T post(String url, String json, RespBodyConvertor<T> convertor) {
        String respBody = post(url, json);
        return convertor.convert(respBody);
    }

    public static String post(String url, MediaType mediaType, String content) {
        return post(url, null, mediaType, content);
    }

    public static <T> T post(String url, MediaType mediaType, String content, RespBodyConvertor<T> convertor) {
        String respBody = post(url, mediaType, content);
        return convertor.convert(respBody);
    }

    public static String post(String url, Headers headers, MediaType mediaType, String content) {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        Request request = builder.url(url)
                                 .post(RequestBody.create(content, mediaType))
                                 .build();
        return getRespBody(request);
    }

    public static <T> T post(String url, Headers headers, MediaType mediaType, String content, RespBodyConvertor<T> convertor) {
        String respBody = post(url, headers, mediaType, content);
        return convertor.convert(respBody);
    }

    @Nonnull
    private static String getRespBody(Request request) {
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new HttpException(response.code(), response.message());
            }
            ResponseBody responseBody = response.body();
            return responseBody != null ? responseBody.string() : "";
        } catch (IOException e) {
            throw new HttpException(-1, e.getMessage());
        }
    }

    /**
     * upload file to the url.
     *
     * @param url  url
     * @param file file to upload
     * @return ResponseBody
     */
    public static ResponseBody upload(String url, File file) {
        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.getName(),
                RequestBody.create(file, MediaType.parse("multipart/form-data")))
            .build();

        Request request = new Request.Builder()
            .header("Authorization", "Client-ID " + UUID.get())
            .url(url)
            .post(requestBody)
            .build();

        try {
            Response response = httpClient.newCall(request).execute();
            return response.body();
        } catch (Exception e) {
            throw new UploadException();
        }
    }


}
