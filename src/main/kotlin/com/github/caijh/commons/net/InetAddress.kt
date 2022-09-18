package com.github.caijh.commons.net

object InetAddress {

    @JvmStatic
    fun getHostAddress(): String {
        return java.net.InetAddress.getLocalHost().hostAddress
    }
}