package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

import com.alibaba.fastjson.JSON;
import com.github.caijh.commons.util.constants.Delimiters;
import com.github.caijh.commons.util.exception.MapKeyException;
import com.github.caijh.commons.util.exception.XMLParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jetbrains.annotations.Nullable;

public class Maps {

    private Maps() {

    }

    /**
     * 将Object对象转化为Map对象.
     *
     * @param obj object
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(obj), Map.class);
    }

    /**
     * 将json转化为map对象.
     *
     * @param json json
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJson(String json) {
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }

    /**
     * @param xml xml string
     * @return map parse from xml string
     */
    public static Map<String, Object> fromXml(String xml) {
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            throw new XMLParseException("Xml Parse Exception", e);
        }
        Element element = document.getRootElement();
        Map<String, Object> map = new HashMap<>();
        fillMap(element, map);
        return map;
    }

    @SuppressWarnings("unchecked")
    private static void fillMap(Element element, Map<String, Object> map) {
        List<Element> elements = element.elements();
        if (Collections.isEmpty(elements)) {
            map.put(element.getName(), element.getTextTrim());
            return;
        }

        Map<String, Object> innerMap = new HashMap<>();
        for (Element e : elements) {
            String eleName = e.getName();
            Object obj = innerMap.get(eleName);
            if (obj == null) {
                fillMap(e, innerMap);
                continue;
            }
            if (obj instanceof java.util.Map) {
                List<Map<String, Object>> list = new ArrayList<>();
                list.add((Map<String, Object>) innerMap.remove(eleName));
                fillMap(e, innerMap);
                list.add((Map<String, Object>) innerMap.remove(eleName));
                innerMap.put(eleName, list);
            } else if (obj instanceof List) {
                List<Object> list = (List<Object>) obj;
                if (list.get(0) instanceof Map) { //判断List下是String还是Map
                    fillMap(e, innerMap);
                    list.add(innerMap.remove(eleName));
                    innerMap.put(eleName, list);
                } else {
                    fillMap(e, innerMap);
                    list.add(e.getTextTrim());
                    innerMap.put(eleName, list);
                }
            } else if (obj instanceof String) { //同一级只有一层的标签
                List<Object> list = new ArrayList<>();
                list.add(obj);
                list.add(e.getTextTrim());
                fillMap(e, innerMap);
                innerMap.put(eleName, list);
            }
        }
        map.put(element.getName(), innerMap);
    }

    /**
     * get the value of map in key.
     *
     * @param map     map object
     * @param keyPath the key link path, like a.b.c
     * @return the value of key
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static Object getValue(@Nonnull Map<String, Object> map, @Nonnull String keyPath) {
        String[] keys = keyPath.split("\\" + Delimiters.DOT);
        Map<String, Object> valueMap = map;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Object o = valueMap.get(key);
            if (i == keys.length - 1) {
                return o;
            }
            if (o == null) {
                throw new MapKeyException("map doesn't contain key, key = " + key);
            }
            if (!Map.class.isAssignableFrom(o.getClass())) {
                throw new MapKeyException("map value is not map class, key = " + key);
            }
            valueMap = ((Map<String, Object>) o);
        }
        return null;
    }

    /**
     * @param map   map
     * @param clazz target object class
     * @param <T>   type parameter
     * @return the object of T
     */
    public static <T> T toObject(Map<?, ?> map, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * @param map1 map 1
     * @param map2 map 2
     * @param <K>  the type of key
     * @param <V>  the type of value
     * @return merge map, same key is replaced by next map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> map = new HashMap<>();
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

}
