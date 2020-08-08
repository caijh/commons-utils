package com.github.caijh.commons.util;

import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

public class BeanUtils {

    private BeanUtils() {
        throw new AssertionError("no instance");
    }

    public static void copyProperties(Object source, Object target, Converter converter) {
        boolean useConverter = converter != null;
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
        copier.copy(source, target, converter);
    }

    /**
     * copy field value from source to target.
     *
     * @param source 原对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, null);
    }

    /**
     * copy ignore null properties.
     *
     * @param source source
     * @param target target
     */
    public static void copyIgnoreNullProperties(Object source, Object target) {
        copyProperties(source, target, (value, valueClass, setMethodName) -> {
            Object o = value;
            if (o == null) { // if value is null, set value become the target object field's value.
                try {
                    String setName = setMethodName.toString();
                    Method getMethod = target.getClass().getMethod("get" + setName.substring("set".length()));
                    o = getMethod.invoke(target);
                } catch (Exception ignored) {
                    // ignored
                }
            }
            return o;
        });
    }

}
