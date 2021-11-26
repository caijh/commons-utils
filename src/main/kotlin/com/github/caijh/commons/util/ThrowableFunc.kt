package com.github.caijh.commons.util

@FunctionalInterface
fun interface ThrowableFunc {
    /**
     * accept throwable and throw it.
     * @param cause throwable
     */
    @Throws(RuntimeException::class)
    fun orElseThrow(cause: Throwable)
}
