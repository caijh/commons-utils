package com.github.caijh.commons.util.lambda

import java.io.Serializable
import java.util.function.Function

@FunctionalInterface
interface SFunction<T, R> : Function<T, R>, Serializable