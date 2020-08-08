package com.github.caijh.commons.util;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClientUtils {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    private HttpClientUtils() {
    }

    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * use http get method to get response content from url.
     *
     * @param url     url
     * @param headers headers
     * @return response content
     * @throws IOException net exception
     */
    public static String get(String url, Headers headers) throws IOException {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        Request request = builder.url(url).build();
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }
        ResponseBody responseBody = response.body();
        return responseBody != null ? responseBody.string() : "";
    }

    public static String post(String url, String json) throws IOException {
        return post(url, null, MediaType.parse(APPLICATION_JSON_UTF8), json);
    }

    public static String post(String url, Headers headers, MediaType mediaType, String content) throws IOException {
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        Request request = builder.url(url)
                                 .post(RequestBody.create(content, mediaType))
                                 .build();
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }
        ResponseBody responseBody = response.body();
        return responseBody != null ? responseBody.string() : "";
    }

}
