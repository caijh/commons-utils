package com.github.caijh.commons.util

class RespBodyNoOpConvertor : RespBodyConvertor<String?> {
    override fun convert(respBody: String?): String {
        return respBody!!
    }

    companion object {
        val INSTANCE = RespBodyNoOpConvertor()
    }
}
