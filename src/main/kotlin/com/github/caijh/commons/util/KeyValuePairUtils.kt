package com.github.caijh.commons.util

import com.github.caijh.commons.util.constants.Delimiters
import org.apache.commons.lang3.StringUtils

/**
 * 键值对工具类.
 *
 * @author caijunhui
 * @since 2017/5/12
 */
object KeyValuePairUtils {

    @JvmStatic
    @JvmOverloads
    fun readAsMap(keyValuePairs: String, delimiter: String = Delimiters.AND): Map<String, String> {
        if (!isKeyValuePair(keyValuePairs)) {
            return emptyMap()
        }
        val map: MutableMap<String, String> = HashMap()
        val pairs = keyValuePairs.split(delimiter).toTypedArray()
        for (pair in pairs) {
            val index = pair.indexOf(Delimiters.EQUAL)
            if (index > -1) {
                map[pair.substring(0, index)] = pair.substring(index + 1)
            }
        }
        return map
    }

    @JvmStatic
    fun <T> mapToBean(keyValuePars: String, clazz: Class<T>?): T {
        val map = readAsMap(keyValuePars, Delimiters.AND)
        return Maps.toObject(map, clazz)
    }

    private fun isKeyValuePair(str: String): Boolean {
        return StringUtils.isNotBlank(str) && str.contains(Delimiters.EQUAL)
    }

    @JvmStatic
    fun map2KeyValuePair(map: Map<String?, String?>): String {
        val sb = StringBuilder()
        map.forEach { (k: String?, v: String?) -> sb.append(k).append("=").append(v).append("&") }
        val s = sb.toString()
        return s.substring(0, s.length - 1)
    }
}
