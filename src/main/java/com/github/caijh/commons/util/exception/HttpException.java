package com.github.caijh.commons.util.exception;

public class HttpException extends RuntimeException {

    private final Integer code;

    public HttpException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
