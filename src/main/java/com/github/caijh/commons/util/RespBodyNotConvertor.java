package com.github.caijh.commons.util;

public class RespBodyNotConvertor implements RespBodyConvertor<String> {

    public static final RespBodyNotConvertor INSTANCE = new RespBodyNotConvertor();

    @Override
    public String convert(String respBody) {
        return respBody;
    }

}
