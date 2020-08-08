package com.github.caijh.commons.util;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.function.Supplier;

import com.github.caijh.commons.util.exception.AssertRuntimeException;

public class AssertUtils {

    private AssertUtils() {
    }

    public static void notNull(Object object) {
        notNull(object, IllegalArgumentException.class, null);
    }

    public static void notNull(Object object, String message) {
        notNull(object, IllegalArgumentException.class, message);
    }

    public static <T extends Exception> void notNull(Object obj, Class<T> exceptionClass) throws T {
        notNull(obj, exceptionClass, null);
    }

    public static <T extends Exception> void notNull(Object object, Class<T> exceptionClass, String message) throws T {
        isTrue(object != null, exceptionClass, message);
    }

    public static <T extends Exception> void eq(Object obj1, Object obj2, Class<T> exceptionClass, String message) throws T {
        isTrue(obj1.equals(obj2), exceptionClass, message);
    }

    public static void notBlank(String str) throws IllegalAccessException {
        isTrue(StringUtils.isNotBlank(str), IllegalAccessException.class, null);
    }

    public static void notEmpty(Collection<?> collection) throws IllegalAccessException {
        isTrue(CollectionUtils.isNotEmpty(collection), IllegalAccessException.class, null);
    }

    public static <T extends Exception> void isTrue(boolean assertIsTrue, Class<T> exceptionClass) throws T {
        isTrue(assertIsTrue, exceptionClass, null);
    }

    public static <T extends Exception> void isTrue(boolean assertIsTrue, Class<T> exceptionClass, String message) throws T {
        if (!assertIsTrue) {
            T instance;
            Constructor<T> constructor;
            try {
                if (message == null) {
                    constructor = ReflectUtils.getConstructor(exceptionClass);
                    instance = constructor.newInstance();
                } else {
                    constructor = ReflectUtils.getConstructor(exceptionClass, String.class);
                    instance = constructor.newInstance(message);
                }
            } catch (Exception ex) {
                throw new AssertRuntimeException();
            }
            throw instance;
        }
    }

    public static <T extends Exception> void isTrue(boolean expression, Supplier<? extends T> exceptionSupplier) throws T {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

}
