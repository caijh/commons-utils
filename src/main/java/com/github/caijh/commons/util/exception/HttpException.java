package com.github.caijh.commons.util.exception;

public class HttpException extends RuntimeException {

    private final int code;

    public HttpException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
