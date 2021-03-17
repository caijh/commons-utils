package com.github.caijh.commons.util;

@FunctionalInterface
public interface RespBodyConvertor<T> {

    T convert(String respBody);

}
