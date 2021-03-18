package com.github.caijh.commons.util;

public class RespBodyNoOpConvertor implements RespBodyConvertor<String> {

    public static final RespBodyNoOpConvertor INSTANCE = new RespBodyNoOpConvertor();

    @Override
    public String convert(String respBody) {
        return respBody;
    }

}
