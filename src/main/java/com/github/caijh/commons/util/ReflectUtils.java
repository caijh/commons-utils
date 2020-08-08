package com.github.caijh.commons.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.github.caijh.commons.util.constants.Delimiters;
import com.github.caijh.commons.util.exception.ReflectRuntimeException;

public class ReflectUtils {

    private ReflectUtils() {
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ReflectRuntimeException(e);
        }
    }

    public static <T> Type[] getParameterTypes(T t) {
        return ((ParameterizedType) (t.getClass().getGenericSuperclass())).getActualTypeArguments();
    }

    public static <T> Constructor<T> getConstructor(Class<T> classType, Class<?>... parameterTypes)
        throws NoSuchMethodException {
        return classType.getConstructor(parameterTypes);
    }

    public static <T> Method getMethod(Class<T> classType, String name, Class<?>... parameterTypes) throws
        NoSuchMethodException {
        return classType.getMethod(name, parameterTypes);
    }


    /**
     * 调用对象的域的Getter方法.
     *
     * @param object     对象
     * @param chainField 对象的域a.b.c
     * @return 对象的域对应getter方法的返回值
     * @throws Exception exception
     */
    public static Object invokeGetter(Object object, String chainField) throws Exception {
        int firstDotIdx = chainField.indexOf(Delimiters.DOT);
        String filed = chainField;
        boolean chain = false;
        if (firstDotIdx > 0) {
            filed = chainField.substring(0, firstDotIdx);
            chain = true;
        }
        Method getMethod = getMethod(object.getClass(), getFiledMethodName("get", filed));
        Object returnObject = getMethod.invoke(object);
        return chain ? invokeGetter(returnObject, chainField.substring(firstDotIdx + 1, chainField.length())) :
            returnObject;
    }

    public static String getFiledMethodName(String action, String filed) {
        return action + StringUtils.firstCharUpperCase(filed);
    }

}
