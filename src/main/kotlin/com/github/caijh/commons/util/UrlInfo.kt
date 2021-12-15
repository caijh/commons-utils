package com.github.caijh.commons.util

import com.github.caijh.commons.util.KeyValuePairUtils.readAsMap
import java.util.regex.Pattern

class UrlInfo private constructor() {
    var schema: String? = null
        private set
    var user: String? = null
        private set
    var password: String? = null
        private set
    var host: String? = null
        private set
    var port: String? = null
        private set
    var path: String? = null
        private set
    var params: Map<String, String>? = null
        private set

    companion object {
        private val URL_PATTERN = Pattern
            .compile("((?<schema>\\w+)://)?((?<user>\\w+):(?<password>\\w+)@)?(?<host>[^/:]+)(:(?<port>\\d*))?(?<path>[^ ]*)")

        @JvmStatic
        fun from(urlString: String?): UrlInfo? {
            var urlInfo: UrlInfo? = null

            if (urlString == null) {
                return urlInfo
            }

            val matcher = URL_PATTERN.matcher(urlString)
            if (matcher.matches()) {
                urlInfo = UrlInfo()
                urlInfo.schema = matcher.group("schema")
                urlInfo.user = matcher.group("user")
                urlInfo.password = matcher.group("password")
                urlInfo.host = matcher.group("host")
                urlInfo.port = matcher.group("port")
                val path = matcher.group("path")
                if (path.contains("?")) {
                    val index = path.indexOf("?")
                    urlInfo.path = path.substring(0, index)
                    val params = path.substring(index + 1)
                    urlInfo.params = readAsMap(params)
                } else {
                    urlInfo.path = path
                }
            }
            return urlInfo
        }

        @JvmStatic
        fun getParam(url: String, key: String): String? {
            val urlInfo = from(url)
            return if (urlInfo != null) {
                urlInfo.params!![key]
            } else null
        }
    }
}
