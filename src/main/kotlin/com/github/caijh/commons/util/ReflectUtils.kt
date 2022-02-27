package com.github.caijh.commons.util

import com.github.caijh.commons.util.Strings.firstCharUpperCase

object ReflectUtils {
    @JvmStatic
    fun getClass(className: String): Class<*> {
        return try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw ReflectRuntimeException(e)
        }
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
        val method = try {
            obj.javaClass.getMethod("get" + firstCharUpperCase(filed))
        } catch (e: Exception) {
            obj.javaClass.getMethod("is" + firstCharUpperCase(filed))
        }
        method.trySetAccessible()
        val returnObject = method.invoke(obj)
        return if (chain) invokeGetter(returnObject, chainField.substring(firstDotIdx + 1)) else returnObject
    }

}
