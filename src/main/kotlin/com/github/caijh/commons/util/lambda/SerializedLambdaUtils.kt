package com.github.caijh.commons.util.lambda

import com.github.caijh.commons.util.SerializedLambdaResolveException
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object SerializedLambdaUtils {
    /**
     * SerializedLambda 反序列化缓存
     */
    private val FUNC_CACHE: MutableMap<Class<*>, WeakReference<SerializedLambda>> = ConcurrentHashMap()

    @JvmStatic
    fun <T> resolve(func: SFunction<T, *>): SerializedLambda {
        val clazz: Class<*> = func.javaClass
        val serializedLambda = Optional.ofNullable(FUNC_CACHE[clazz])
            .map { obj: WeakReference<SerializedLambda> -> obj.get() }
            .orElseGet {
                SerializedLambda.resolve(func)
            } ?: throw SerializedLambdaResolveException("Resolve SerializedLambda Fail.")

        FUNC_CACHE[clazz] = WeakReference(serializedLambda)
        return serializedLambda
    }


}
