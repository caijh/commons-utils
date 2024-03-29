package com.github.caijh.commons.util.reflection.property

import com.github.caijh.commons.util.PropertyResolveException
import java.lang.reflect.Method
import java.util.*

object PropertyNamer {

    @JvmStatic
    fun methodToProperty(methodName: String): String {
        var propertyName = methodName
        propertyName = if (propertyName.startsWith("is")) {
            propertyName.substring(2)
        } else if (propertyName.startsWith("get") || propertyName.startsWith("set")) {
            propertyName.substring(3)
        } else {
            throw PropertyResolveException("Error parsing method name '$propertyName'.  Didn't start with 'is', 'get' or 'set'.")
        }
        if (propertyName.length == 1 || propertyName.length > 1 && !Character.isUpperCase(propertyName[1])) {
            propertyName = propertyName.substring(0, 1).lowercase(Locale.ENGLISH) + propertyName.substring(1)
        }
        return propertyName
    }

    @JvmStatic
    fun isProperty(name: String): Boolean {
        return isGetter(name) || isSetter(name)
    }

    @JvmStatic
    fun isGetter(name: String): Boolean {
        return name.startsWith("get") && name.length > 3 || name.startsWith("is") && name.length > 2
    }

    @JvmStatic
    fun isSetter(name: String): Boolean {
        return name.startsWith("set") && name.length > 3
    }

    @JvmStatic
    fun propertyToGetterMethod(clazz: Class<*>, property: String): Method? {
        return clazz.methods.filter { e -> isGetter(e.name) && methodToProperty(e.name) == property }.getOrNull(0)
    }
}
