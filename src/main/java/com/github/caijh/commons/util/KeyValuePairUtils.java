package com.github.caijh.commons.util;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.caijh.commons.util.constants.Delimiters;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 键值对工具类.
 *
 * @author caijunhui
 * @since 2017/5/12
 */
public class KeyValuePairUtils {

    private KeyValuePairUtils() {

    }

    public static Map<String, String> readAsMap(String keyValuePairs, String delimiter) {
        if (!isKeyValuePair(keyValuePairs)) {
            return Collections.emptyMap();
        }

        if (delimiter == null) {
            delimiter = Delimiters.AND;
        }

        Map<String, String> map = new HashMap<>();
        String[] pairs = keyValuePairs.split(delimiter);
        for (String pair : pairs) {
            int index = pair.indexOf(Delimiters.EQUAL);
            if (index > -1) {
                map.put(pair.substring(0, index), pair.substring(index + 1, pair.length()));
            }
        }
        return map;
    }

    public static Map<String, String> readAsMap(String keyValuePairs) {
        return readAsMap(keyValuePairs, Delimiters.AND);
    }

    public static <T> T mapToBean(String keyValuePars, Class<T> clazz) throws Exception {
        Map<String, String> map = readAsMap(keyValuePars, Delimiters.AND);
        T bean = clazz.newInstance();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Field field = Arrays.stream(clazz.getDeclaredFields()).filter(e -> e.getName().equals(key)).findFirst().orElse(null);
            if (field != null) {
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
        return bean;
    }

    public static boolean isKeyValuePair(String str) {
        return isNotBlank(str) && str.contains(Delimiters.EQUAL);
    }

}
