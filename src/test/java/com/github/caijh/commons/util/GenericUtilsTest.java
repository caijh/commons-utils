package com.github.caijh.commons.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericUtilsTest {

    @Test
    public void test_getSuperClassGenericType() {
        Class<?> superClassGenericType = GenericUtils.getSuperClassGenericType(B.class);
        assertEquals(String.class, superClassGenericType);
    }

    public static class A<T> {

        void a(T a) {
            System.out.println(a);
        }

    }

    public static class B extends A<String> {

    }

}
