package com.github.caijh.commons.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void fromXml() {
        String xml = "<xml><a><a1>1</a1><a2>2</a2></a></xml>";
        Map<String, Object> map = Maps.fromXml(xml);
    }

    @Test
    public void toObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "aaa");
        map.put("age", 20);
        User user = Maps.toObject(map, User.class);

        assertEquals("aaa", user.getName());
    }

    @Test
    public void merge() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "test");
        map1.put("age", "20");
        Map<String, String> map2 = new HashMap<>();
        map1.put("name", "test2");
        map1.put("age2", "20");
        Map<String, String> merge = Maps.merge(map1, map2);
        assertTrue(merge.containsKey("name") && merge.get("name").equals("test2"));
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
