package com.github.caijh.commons.util

import com.github.caijh.commons.util.reflection.invoker.GetFieldInvoker

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
        var fieldName = chainField
        var chain = false
        if (firstDotIdx > 0) {
            fieldName = chainField.substring(0, firstDotIdx)
            chain = true
        }

        val field = try {
            obj.javaClass.getDeclaredField(fieldName)
        } catch (e: NoSuchFieldException) {
            obj.javaClass.getField(fieldName)
        }
        val returnObject = GetFieldInvoker(field).invoke(obj)

        return if (chain) invokeGetter(returnObject, chainField.substring(firstDotIdx + 1)) else returnObject
    }

}
