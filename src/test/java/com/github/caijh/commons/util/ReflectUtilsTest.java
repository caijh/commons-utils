package com.github.caijh.commons.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * @author caijunhui
 **/
public class ReflectUtilsTest {

    @Test
    public void testInvokeGetter() throws Exception {
        C c = new C();
        B b = new B();
        A a = new A();
        a.setA(10);
        b.setA(a);
        b.setB(20);
        c.setC(30);
        c.setB(b);

        System.out.println(ReflectUtils.invokeGetter(c, "b.a.a"));
    }

    @Test
    public void testD() {
        D<Integer, Integer> d = new D<Integer, Integer>() {
        };
        TypeVariable<? extends Class<? extends D>>[] typeParameters = d.getClass().getTypeParameters();
        for (int i = 0; i < typeParameters.length; i++) {
            System.out.println(typeParameters[i]);
            System.out.println(typeParameters[i].getName());
            System.out.println(typeParameters[i].getTypeName());
            System.out.println(typeParameters[i].getGenericDeclaration().getName());


        }

        Type genericSuperclass = d.getClass().getGenericSuperclass();
        System.out.println(genericSuperclass.getTypeName());

        Type[] genericInterfaces = d.getClass().getGenericInterfaces();
        for (int i = 0; i < genericInterfaces.length; i++) {
            System.out.println(genericInterfaces[i].getTypeName());
        }


        Type[] actualTypeArguments = ((ParameterizedType) d.getClass().getGenericSuperclass()).getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            System.out.println(actualTypeArguments[i].getTypeName());
        }

        List<D> dList = new ArrayList<>();
        System.out.println(Collection.class.isAssignableFrom(dList.getClass()));
        System.out.println(((ParameterizedType) dList.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public class A {

        private Integer a;

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

    }

    public class B {

        private A a;
        private Integer b;


        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

    }

    public class C {

        private Integer c;
        private B b;

        public Integer getC() {
            return c;
        }

        public void setC(Integer c) {
            this.c = c;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

    }

    public abstract class D<T1, T2> {

        T1 obj1;
        T2 obj2;

        public T1 getObj1() {
            return obj1;
        }

        public void setObj1(T1 obj1) {
            this.obj1 = obj1;
        }

        public T2 getObj2() {
            return obj2;
        }

        public void setObj2(T2 obj2) {
            this.obj2 = obj2;
        }

    }

}
