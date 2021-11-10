package com.github.caijh.commons.util

import java.lang.reflect.*

/**
 * 泛型工具类
 */
object GenericUtils {

    /**
     * 获取得指定类的父类的第一个泛型参数的实际类型，如： `UserBusinessImpl extends GboatDAO<User>`
     *
     * @param clazz 泛型类的具体实现了类，如： UserBusinessImpl.class
     * @return 泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getSuperClassGenericType(clazz: Class<*>): Class<*> {
        return getSuperClassGenericType(clazz, 0)
    }

    /**
     * 获取指定类的父类的泛型参数的实际类型，如： `UserBusinessImpl extends GboatDAO<User>`
     *
     * @param clazz 泛型类的具体实现了类，如： UserBusinessImpl.class
     * @param index 泛型参数所在索引，从0开始
     * @return 泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getSuperClassGenericType(clazz: Class<*>, index: Int): Class<*> {
        val genType = clazz.genericSuperclass // 得到泛型父类

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        return if (genType !is ParameterizedType) {
            Any::class.java
        } else getGenericClass(genType, index)
    }

    /**
     * 获取全局成员变量（Field）的第一个泛型参数的实际类型，如： `List<User&gt users;`
     *
     * @param field 成员变量的定义
     * @return 成员变量（Field）的第一个泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getFieldGenericType(field: Field): Class<*> {
        return getFieldGenericType(field, 0)
    }

    /**
     * 获取全局成员变量（Field）的泛型参数的实际类型，如： `Map<String, User&gt users;`
     *
     * @param field 成员变量的定义
     * @param index 泛型参数所在索引，从0开始
     * @return 成员变量（Field）的泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getFieldGenericType(field: Field, index: Int): Class<*> {
        val genericFieldType = field.genericType

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        return if (genericFieldType !is ParameterizedType) {
            Any::class.java
        } else getGenericClass(genericFieldType, index)
    }

    /**
     * 获取方法返回类型的第一个泛型参数的实际类型，如： `public List<User> queryUsers(){}`
     *
     * @param method 方法
     * @return 方法的返回类型的第一个泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getMethodReturnGenericType(method: Method): Class<*> {
        return getMethodReturnGenericType(method, 0)
    }

    /**
     * 获取方法返回类型的泛型参数的实际类型，如： `public Map<String, User> queryUsers(){}`
     *
     * @param method 方法
     * @param index  泛型参数所在索引，从0开始
     * @return 方法的返回类型泛型参数的实际类型，如果传入的 clazz 没有实现 ParameterizedType 接口，即不支持泛型时，则直接返回
     * `Object.class`
     */
    fun getMethodReturnGenericType(method: Method, index: Int): Class<*> {
        val returnType = method.genericReturnType

        // 如果没有实现 ParameterizedType 接口，即不支持泛型，直接返回Object.class
        return if (returnType !is ParameterizedType) {
            Any::class.java
        } else getGenericClass(returnType, index)
    }

    /**
     * 获取方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型，如 void method(Map&lt;String, User&gt;
     * maps, List&lt;Group&gt; groups){}
     *
     * @param method 方法
     * @return 方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型
     */
    fun getMethodParameterGenericTypes(method: Method): List<Class<*>> {
        return getMethodParameterGenericTypes(method, 0)
    }

    /**
     * 获取方法输入参数中带有泛型的输入参数的所有泛型参数的实际类型，如 void method(Map&lt;String, User&gt;
     * maps, List&lt;Group&gt; groups){}
     *
     * @param method 方法
     * @param index  带泛型的输入参数的索引
     * @return 方法输入参数中第一个带有泛型的输入参数的所有泛型参数的实际类型
     */
    fun getMethodParameterGenericTypes(method: Method, index: Int): List<Class<*>> {
        val results: MutableList<Class<*>> = ArrayList()
        val genericParameterTypes = method.genericParameterTypes
        if (index < 0 || index >= genericParameterTypes.size) {
            throw GenericException(
                "传入的索引 [" + index + "] "
                    + if (index < 0) "不能小于0" else "超出了泛型参数的总数 [" + genericParameterTypes.size + "]"
            )
        }
        val genericParameterType = genericParameterTypes[index]
        if (genericParameterType is ParameterizedType) {
            val parameterArgTypes = genericParameterType.actualTypeArguments
            for (parameterArgType in parameterArgTypes) {
                results.add(parameterArgType as Class<*>)
            }
        }
        return results
    }

    /**
     * 获取泛型参数的实际类型
     *
     * @param parameterizedType 泛型
     * @param index             泛型参数的位置索引， 从 0 开始
     * @return 泛型参数的实际类型
     */
    fun getGenericClass(parameterizedType: ParameterizedType, index: Int): Class<*> {
        return when (val genericClass: Any = parameterizedType.actualTypeArguments[index]) {
            is ParameterizedType -> { // 处理多级泛型
                genericClass.rawType as Class<*>
            }
            is GenericArrayType -> { // 处理数组泛型
                genericClass.genericComponentType as Class<*>
            }
            is TypeVariable<*> -> { // 处理泛型擦拭对象
                convertType2Class(genericClass.bounds[0], 0)
            }
            else -> {
                genericClass as Class<*>
            }
        }
    }

    private fun convertType2Class(type: Type, index: Int): Class<*> {
        return when (type) {
            is ParameterizedType -> { // 处理泛型类型
                getGenericClass(type, index)
            }
            is TypeVariable<*> -> {
                convertType2Class(type.bounds[0], 0) // 处理泛型擦拭对象
            }
            else -> { // class本身也是type，强制转型
                type as Class<*>
            }
        }
    }
}
