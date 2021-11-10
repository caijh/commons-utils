package com.github.caijh.commons.util

import com.github.caijh.commons.util.Strings.firstCharUpperCase
import java.lang.reflect.Constructor
import java.lang.reflect.Method

object ReflectUtils {
    fun getClass(className: String?): Class<*> {
        return try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw ReflectRuntimeException(e)
        }
    }

    @Throws(NoSuchMethodException::class)
    fun <T> getConstructor(classType: Class<T>, vararg parameterTypes: Class<*>?): Constructor<T> {
        return classType.getConstructor(*parameterTypes)
    }

    @Throws(NoSuchMethodException::class)
    fun <T> getMethod(classType: Class<T>, name: String, vararg parameterTypes: Class<*>?): Method {
        return classType.getMethod(name, *parameterTypes)
    }

    /**
     * 调用对象的域的Getter方法.
     *
     * @param obj     对象
     * @param chainField 对象的域a.b.c
     * @return 对象的域对应getter方法的返回值
     * @throws Exception exception
     */
    @JvmStatic
    @Throws(Exception::class)
    fun invokeGetter(obj: Any, chainField: String): Any {
        val firstDotIdx = chainField.indexOf(Delimiters.DOT)
        var filed = chainField
        var chain = false
        if (firstDotIdx > 0) {
            filed = chainField.substring(0, firstDotIdx)
            chain = true
        }
        val getMethod = getMethod(obj.javaClass, "get" + firstCharUpperCase(filed))
        val returnObject = getMethod.invoke(obj)
        return if (chain) invokeGetter(returnObject, chainField.substring(firstDotIdx + 1)) else returnObject
    }

}
