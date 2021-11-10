package com.github.caijh.commons.util

import com.alibaba.fastjson.JSON
import com.github.caijh.commons.util.Collections.emptyMap
import com.github.caijh.commons.util.Collections.isEmpty
import org.dom4j.Document
import org.dom4j.DocumentException
import org.dom4j.DocumentHelper
import org.dom4j.Element
import java.util.function.Consumer

object Maps {
    /**
     * 将Object对象转化为Map对象.
     *
     * @param obj object
     * @return map
     */
    @JvmStatic
    fun fromObject(obj: Any?): Map<String, Any> {
        return if (obj == null) {
            emptyMap()
        } else JSON.parseObject<Map<String, Any>>(
            JSON.toJSONString(obj),
            MutableMap::class.java
        )
    }

    /**
     * 将json转化为map对象.
     *
     * @param json json
     * @return map
     */
    @JvmStatic
    fun fromJson(json: String?): Map<String, Any> {
        return if (json == null) {
            emptyMap()
        } else JSON.parseObject<Map<String, Any>>(
            json,
            MutableMap::class.java
        )
    }

    /**
     * 将xml字符串转化为Map对象.
     * @param xml xml string
     * @return map parse from xml string
     */
    @JvmStatic
    fun fromXml(xml: String?): Map<String, Any> {
        val document: Document = try {
            DocumentHelper.parseText(xml)
        } catch (e: DocumentException) {
            throw XMLParseException("Xml Parse Exception", e)
        }
        val element = document.rootElement
        val map: MutableMap<String, Any> = HashMap()
        fillMap(element, map)
        return map
    }

    private fun fillMap(element: Element, map: MutableMap<String, Any>) {
        val elements = element.elements()
        if (isEmpty(elements)) {
            map[element.name] = element.textTrim
            return
        }
        val innerMap: MutableMap<String, Any> = HashMap()
        for (e in elements) {
            val eleName = e.name
            val obj = innerMap[eleName]
            if (obj == null) {
                fillMap(e, innerMap)
                continue
            }
            if (obj is Map<*, *>) {
                val list: MutableList<Map<String, Any>?> = ArrayList()
                list.add(innerMap.remove(eleName) as Map<String, Any>?)
                fillMap(e, innerMap)
                list.add(innerMap.remove(eleName) as Map<String, Any>?)
                innerMap[eleName] = list
            } else if (obj is List<*>) {
                val list = obj as MutableList<Any?>
                if (list[0] is Map<*, *>) { //判断List下是String还是Map
                    fillMap(e, innerMap)
                    list.add(innerMap.remove(eleName))
                    innerMap[eleName] = list
                } else {
                    fillMap(e, innerMap)
                    list.add(e.textTrim)
                    innerMap[eleName] = list
                }
            } else if (obj is String) { //同一级只有一层的标签
                val list: MutableList<Any> = ArrayList()
                list.add(obj)
                list.add(e.textTrim)
                fillMap(e, innerMap)
                innerMap[eleName] = list
            }
        }
        map[element.name] = innerMap
    }

    /**
     * get the value of map in key.
     *
     * @param map     map object
     * @param keyPath the key link path, like a.b.c
     * @return the value of key
     */
    @JvmStatic
    fun getValue(map: Map<String, Any>, keyPath: String): Any? {
        val keys = keyPath.split(("\\" + Delimiters.DOT).toRegex()).toTypedArray()
        var valueMap = map
        for (i in keys.indices) {
            val key = keys[i]
            val o = valueMap[key]
            if (i == keys.size - 1) {
                return o
            }
            if (o == null) {
                throw MapKeyException("map doesn't contain key, key = $key")
            }
            if (!Map::class.java.isAssignableFrom(o.javaClass)) {
                throw MapKeyException("map value is not map class, key = $key")
            }
            valueMap = o as Map<String, Any>
        }
        return null
    }

    /**
     * get values from map.
     *
     * @param map  the map
     * @param keys the key to find
     * @param <K>  k type
     * @param <V>  value type
     * @return list of value
    </V></K> */
    @JvmStatic
    fun <K, V> getValues(map: Map<K, V>, keys: List<K>): List<V> {
        val list: MutableList<V> = ArrayList()
        keys.forEach(Consumer { k: K ->
            val v = map[k]
            if (v != null) {
                list.add(v)
            }
        })
        return list
    }

    /**
     * convert map to object.
     * @param map   map
     * @param clazz target object class
     * @param <T>   type parameter
     * @return the object of T
    </T> */
    @JvmStatic
    fun <T> toObject(map: Map<*, *>, clazz: Class<T>): T {
        return JSON.parseObject(JSON.toJSONString(map), clazz)
    }

    /**
     * @param map1 map 1
     * @param map2 map 2
     * @param <K>  the type of key
     * @param <V>  the type of value
     * @return merge map, same key is replaced by next map
    </V></K> */
    @JvmStatic
    fun <K, V> merge(map1: Map<K, V>, map2: Map<K, V>): Map<K, V> {
        val map: MutableMap<K, V> = HashMap()
        map.putAll(map1)
        map.putAll(map2)
        return map
    }
}
