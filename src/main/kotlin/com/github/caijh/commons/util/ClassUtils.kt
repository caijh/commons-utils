package com.github.caijh.commons.util

object ClassUtils {
    @JvmStatic
    fun toClassConfident(name: String?): Class<*> {
        return try {
            Class.forName(name, false, defaultClassLoader)
        } catch (e: ClassNotFoundException) {
            try {
                Class.forName(name)
            } catch (ex: ClassNotFoundException) {
                throw ClassLoadException("找不到指定的class！请仅在明确确定会有 class 的时候，调用该方法", e)
            }
        }
    }

    private val defaultClassLoader: ClassLoader?
        get() {
            var cl: ClassLoader? = null
            try {
                cl = Thread.currentThread().contextClassLoader
            } catch (ex: Throwable) {
                // Cannot access thread context ClassLoader - falling back...
            }
            if (cl == null) {
                // No thread context class loader -> use class loader of this class.
                cl = ClassUtils::class.java.classLoader
                if (cl == null) {
                    // getClassLoader() returning null indicates the bootstrap ClassLoader
                    try {
                        cl = ClassLoader.getSystemClassLoader()
                    } catch (ex: Throwable) {
                        // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                    }
                }
            }
            return cl
        }
}