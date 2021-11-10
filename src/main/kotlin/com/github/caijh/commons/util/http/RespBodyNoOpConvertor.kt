package com.github.caijh.commons.util.http

class RespBodyNoOpConvertor : RespBodyConvertor<String?> {
    override fun convert(respBody: String?): String {
        return respBody!!
    }

    companion object {
        val INSTANCE = RespBodyNoOpConvertor()
    }
}
