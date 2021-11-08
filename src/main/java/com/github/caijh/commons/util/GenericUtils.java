package com.github.caijh.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import com.github.caijh.commons.util.exception.GenericException;

/**
 * 泛型工具类
 */
public class GenericUtils {

    /**
     * 将构造方法设为私有，防止在外部被实例化
     */
    private GenericUtils() {

    }

    /**
     * 获取得指定类的父类的第一个泛型参数的实际类型，如： <code>UserBusinessImpl extends GboatDAO&lt;User&gt;</code>
     *
     * @param clazz 泛型类的具体实现了类，如： UserBusinessImpl.class
     * @return 泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 获取指定类的父类的泛型参数的实际类型，如： <code>UserBusinessImpl extends GboatDAO&lt;User&gt;</code>
     *
     * @param clazz 泛型类的具体实现了类，如： UserBusinessImpl.class
     * @param index 泛型参数所在索引，从0开始
     * @return 泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass(); // 得到泛型父类

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        return getGenericClass((ParameterizedType) genType, index);
    }

    /**
     * 获取全局成员变量（Field）的第一个泛型参数的实际类型，如： <code>List&lt;User&gt users;</code>
     *
     * @param field 成员变量的定义
     * @return 成员变量（Field）的第一个泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getFieldGenericType(Field field) {
        return getFieldGenericType(field, 0);
    }

    /**
     * 获取全局成员变量（Field）的泛型参数的实际类型，如： <code>Map&lt;String, User&gt users;</code>
     *
     * @param field 成员变量的定义
     * @param index 泛型参数所在索引，从0开始
     * @return 成员变量（Field）的泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getFieldGenericType(Field field, int index) {
        Type genericFieldType = field.getGenericType();

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        if (!(genericFieldType instanceof ParameterizedType)) {
            return Object.class;
        }

        return getGenericClass((ParameterizedType) genericFieldType, index);
    }

    /**
     * 获取方法返回类型的第一个泛型参数的实际类型，如： <code>public List&lt;User&gt; queryUsers(){}</code>
     *
     * @param method 方法
     * @return 方法的返回类型的第一个泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getMethodReturnGenericType(Method method) {
        return getMethodReturnGenericType(method, 0);
    }

    /**
     * 获取方法返回类型的泛型参数的实际类型，如： <code>public Map&lt;String, User&gt; queryUsers(){}</code>
     *
     * @param method 方法
     * @param index  泛型参数所在索引，从0开始
     * @return 方法的返回类型泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * <code>Object.class</code>
     */
    public static Class<?> getMethodReturnGenericType(Method method, int index) {
        Type returnType = method.getGenericReturnType();

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        if (!(returnType instanceof ParameterizedType)) {
            return Object.class;
        }

        return getGenericClass((ParameterizedType) returnType, index);
    }

    /**
     * 获取方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型，如 void method(Map&lt;String, User&gt;
     * maps, List&lt;Group&gt; groups){}
     *
     * @param method 方法
     * @return 方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型
     */
    public static List<Class<?>> getMethodParameterGenericTypes(Method method) {
        return getMethodParameterGenericTypes(method, 0);
    }

    /**
     * 获取方法输入参数中带有泛型的输入参数的所有泛型参数的实际类型，如 void method(Map&lt;String, User&gt;
     * maps, List&lt;Group&gt; groups){}
     *
     * @param method 方法
     * @param index  带泛型的输入参数的索引
     * @return 方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型
     */
    public static List<Class<?>> getMethodParameterGenericTypes(Method method, int index) {
        List<Class<?>> results = new ArrayList<>();
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        if (index < 0 || index >= genericParameterTypes.length) {
            throw new GenericException("传入的索引 [" + index + "] "
                + (index < 0 ? "不能小于0" : "超出了泛型参数的总数 [" + genericParameterTypes.length + "]"));
        }

        Type genericParameterType = genericParameterTypes[index];
        if (genericParameterType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericParameterType;
            Type[] parameterArgTypes = aType.getActualTypeArguments();
            for (Type parameterArgType : parameterArgTypes) {
                results.add((Class<?>) parameterArgType);
            }
        }
        return results;
    }

    /**
     * 获取泛型参数的实际类型
     *
     * @param parameterizedType 泛型
     * @param index             泛型参数的位置索引， 从 0 开始
     * @return 泛型参数的实际类型
     */
    public static Class<?> getGenericClass(ParameterizedType parameterizedType, int index) {
        Object genericClass = parameterizedType.getActualTypeArguments()[index];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class<?>) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return convertType2Class(((TypeVariable<?>) genericClass).getBounds()[0], 0);
        } else {
            return (Class<?>) genericClass;
        }
    }

    private static Class<?> convertType2Class(Type type, int index) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, index);
        } else if (type instanceof TypeVariable) {
            return convertType2Class(((TypeVariable<?>) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else { // class本身也是type，强制转型
            return (Class<?>) type;
        }
    }

}
