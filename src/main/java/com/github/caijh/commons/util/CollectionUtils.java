package com.github.caijh.commons.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * CollectionUtils.
 *
 * @author caijh
 * @since 2017-2-21.
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static <T> Set<T> toHashSet(List<T> list) {
        return new HashSet<>(list);
    }

    /**
     * translate array or collection to LinkedList.
     *
     * @param obj obj
     * @return LinkedList
     */
    @SuppressWarnings("unchecked")
    public static List<?> toLinkedList(Object obj) {
        List<?> list = null;
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (Collection.class.isAssignableFrom(obj.getClass())) {
            list = new LinkedList<>((Collection) obj);
        }
        if (list == null) {
            throw new IllegalArgumentException("obj is not array or collection");
        }
        return list;
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

}
