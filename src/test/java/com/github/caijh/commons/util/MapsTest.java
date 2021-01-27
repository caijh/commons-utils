package com.github.caijh.commons.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MapsTest {


    @Test
    public void fromObject() {
        User user = new User();
        user.name = "caijh";
        user.age = 20;
        Map<String, Object> stringObjectMap = Maps.fromObject(user);
        assertTrue(stringObjectMap.containsKey("name") && stringObjectMap.get("name").equals(user.name));
    }

    @Test
    public void fromJson() {
        User user = new User();
        user.name = "caijh";
        user.age = 20;
        Map<String, Object> stringObjectMap = Maps.fromJson(JSON.toJSONString(user));
        assertTrue(stringObjectMap.containsKey("name") && stringObjectMap.get("name").equals(user.name));
    }

    public static class User {

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
