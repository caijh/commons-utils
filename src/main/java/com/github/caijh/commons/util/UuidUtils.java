package com.github.caijh.commons.util;

import java.util.UUID;

import com.github.caijh.commons.util.constants.Delimiters;

@Deprecated
public class UuidUtils {

    private UuidUtils() {

    }

    public static String uuid() {
        return uuid(true, true);
    }

    public static String uuid(boolean replaceDash) {
        return uuid(true, replaceDash);
    }

    public static String uuid(boolean toUpperCase, boolean replaceDash) {
        String uuid = UUID.randomUUID().toString();
        if (replaceDash) {
            uuid = uuid.replace(Delimiters.DASH, "");
        }
        if (toUpperCase) {
            uuid = uuid.toUpperCase();
        }
        return uuid;
    }

}
