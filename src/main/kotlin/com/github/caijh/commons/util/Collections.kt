package com.github.caijh.commons.util

import java.util.*
import java.util.Collections
import javax.annotation.Nonnull

/**
 * CollectionUtils.
 *
 * @author caijunhui
 * @since 2017-2-21.
 */
object Collections {

    @JvmStatic
    fun <T> emptyList(): List<T> {
        return Collections.emptyList()
    }

    fun <T> emptySet(): Set<T> {
        return Collections.emptySet()
    }

    @JvmStatic
    fun <T> toHashSet(list: List<T>?): Set<T> {
        return HashSet(list)
    }

    @JvmStatic
    fun <T> toLinkedList(@Nonnull collection: Collection<T>?): List<T> {
        return LinkedList(collection)
    }

    @JvmStatic
    fun <T> toLinkedList(@Nonnull array: Array<T>): List<T> {
        return LinkedList(listOf(*array))
    }

    @JvmStatic
    fun <T> isEmpty(collection: Collection<T>?): Boolean {
        return collection == null || collection.isEmpty()
    }

    @JvmStatic
    fun <T> isNotEmpty(collection: Collection<T>?): Boolean {
        return !isEmpty(collection)
    }
}
