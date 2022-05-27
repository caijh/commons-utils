package com.github.caijh.commons.util.lambda

import com.github.caijh.commons.util.reflection.property.PropertyNamer

object PropertyResolver {

    /**
     * 获取get、set方法对应属性名称.
     * methodToProperty必须由java类调用.
     */
    @JvmStatic
    fun <T> methodToProperty(func: SFunction<T, *>): String {
        val lambda = SerializedLambdaUtils.resolve(func)
        return PropertyNamer.methodToProperty(lambda.implMethodName)
    }

}