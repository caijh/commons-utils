package com.github.caijh.commons.util;

import org.junit.Test;

public class ObjectTest {

    public void f(Object o) {
        System.out.println(o.getClass());
    }

    @Test
    public void test() {
        A a = new A();
        a.setA(1);
        f(a);
    }

    public static class A {

        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

    }

}
