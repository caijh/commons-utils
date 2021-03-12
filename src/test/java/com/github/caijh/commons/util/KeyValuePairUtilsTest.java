package com.github.caijh.commons.util;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KeyValuePairUtilsTest {

    @Test
    public void test_readAsMap() {
        String pair = "a=1&b=2&c=3";
        Map<String, String> map = KeyValuePairUtils.readAsMap(pair);

        assertTrue(map.containsKey("a") && map.containsKey("b") && map.containsKey("c"));

        String keyValuePair = KeyValuePairUtils.map2KeyValuePair(map);

        assertEquals(pair, keyValuePair);

    }

    @Test
    public void test_mapToBean() throws Exception {
        String pair = "a=1&b=2";
        Ab ab = KeyValuePairUtils.mapToBean(pair, Ab.class);

        assertTrue(ab.getA().equals("1") && ab.getB().equals("2"));
    }

    public static class Ab {

        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

    }

}
