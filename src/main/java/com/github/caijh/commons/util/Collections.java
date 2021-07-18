package com.github.caijh.commons.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 * CollectionUtils.
 *
 * @author caijh
 * @since 2017-2-21.
 */
public class Collections {

    private Collections() {
    }

    public static <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }

    public static <T> Set<T> emptySet() {
        return java.util.Collections.emptySet();
    }

    public static <T> Set<T> toHashSet(List<T> list) {
        return new HashSet<>(list);
    }

    public static <T> List<T> toLinkedList(@Nonnull Collection<T> collection) {
        return new LinkedList<>(collection);
    }

    public static <T> List<T> toLinkedList(@Nonnull T[] array) {
        return new LinkedList<>(Arrays.asList(array));
    }


    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

}
