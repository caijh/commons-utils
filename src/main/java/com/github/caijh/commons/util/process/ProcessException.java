package com.github.caijh.commons.util.process;

public class ProcessException extends RuntimeException {

    public ProcessException(String message, Exception e) {
        super(message, e);
    }

}
