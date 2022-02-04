package com.github.caijh.commons.util;

import org.junit.Assert;
import org.junit.Test;

public class BeanUtilsTest {

    @Test
    public void copyProperties() {
        A a = new A();
        a.setName("test");
        B b = new B();
        b.setAge(10);
        BeanUtils.copyProperties(a, b);
        Assert.assertNull(b.getAge());

        B b2 = new B();
        b2.setAge(10);
        BeanUtils.copyIgnoreNullProperties(a, b);
        Assert.assertEquals(10, b2.getAge().intValue());

        B b3 = new B();
        BeanUtils.copyProperties(a, b3, (o, aClass, o1) -> {
            System.out.println(o);
            System.out.println(aClass);
            System.out.println(o1);
            return o;
        });

        Assert.assertEquals(a.getName(), b3.getName());

        a.setAge(20);
        B b4 = new B();
        BeanUtils.copyProperties(a, b4, "age");

        Assert.assertNull(b4.getAge());

    }

    static class A {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

    }

    static class B {

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

    }

}
