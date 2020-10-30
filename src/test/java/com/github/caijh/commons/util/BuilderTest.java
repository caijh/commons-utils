package com.github.caijh.commons.util;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

class BuilderTest {

    @Test
    public void build() {
        Person person = Builder.of(Person::new)
                               .with(Person::setName, "caijh")
                               .with(Person::setAge, 20)
                               .build();
        assertEquals("caijh", person.getName());
        assertEquals(20, (int) person.getAge());
    }

    static class Person {

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
