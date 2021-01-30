package com.github.caijh.commons.util;

import java.util.function.Supplier;

/**
 * 断言工具类.
 */
public class Asserts {

    private Asserts() {}

    public static void notNull(Object object) {
        notNull(object, NullPointerException::new);
    }


    public static <T extends Throwable> void notNull(Object object, Supplier<T> supplier) throws T {
        if (object == null) {
            throw supplier.get();
        }
    }

    public static <T extends Exception> void isTrue(boolean expression, Supplier<T> supplier) throws T {
        if (!expression) {
            throw supplier.get();
        }
    }

}
