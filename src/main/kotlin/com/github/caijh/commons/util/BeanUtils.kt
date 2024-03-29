package com.github.caijh.commons.util

import com.github.caijh.commons.util.reflection.invoker.GetFieldInvoker
import com.github.caijh.commons.util.reflection.property.PropertyNamer
import net.sf.cglib.beans.BeanCopier
import net.sf.cglib.core.Converter

/**
 * BeanCopier工具类
 */
@Deprecated("use Spring BeanUtils instead")
class BeanUtils private constructor() {
    companion object {
        /**
         * copy field value from source to target.
         *
         * @param source 原对象
         * @param target 目标对象
         */
        @JvmStatic
        @JvmOverloads
        fun copyProperties(source: Any, target: Any, converter: Converter? = null) {
            val useConverter = converter != null
            val copier = BeanCopier.create(source.javaClass, target.javaClass, useConverter)
            copier.copy(source, target, converter)
        }

        /**
         * copy ignore null properties.
         *
         * @param source source
         * @param target target
         */
        @JvmStatic
        fun copyIgnoreNullProperties(source: Any, target: Any) {
            copyProperties(source, target) { value: Any?, _: Class<*>?, setMethodName: Any ->
                var o = value
                if (o == null) { // if value is null, set value become the target object field's value.
                    try {
                        val setName = setMethodName.toString()
                        val property = PropertyNamer.methodToProperty(setName)
                        val field = target.javaClass.getDeclaredField(property)
                        o = GetFieldInvoker(field).invoke(target)
                    } catch (ignored: Exception) {
                        // ignored
                    }
                }
                o
            }
        }

        @JvmStatic
        fun copyProperties(
            source: Any,
            target: Any,
            vararg ignoreProperties: String
        ) {
            copyProperties(source, target) { value: Any?, _: Class<*>?, setMethodName: Any ->
                val setName = setMethodName.toString()
                val propertyName = Strings.firstCharLowerCase(setName.substring("set".length))
                if (propertyName in ignoreProperties) {
                    null
                } else {
                    value
                }
            }
        }
    }

    init {
        throw AssertionError("no instance")
    }
}
