package com.github.caijh.commons.util;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

public class HttpClientUtilsTests {

    @Test
    @Ignore
    public void testGet() throws IOException {
        String url = "http://www.baidu.com";
        System.out.println(HttpClientUtils.get(url));
    }

    @Test
    public void testCLInviteCode() throws IOException {
        String clCheckInviteCodeUrl = "";
        String inviteCode = "";

        final char[] numbersAlphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        final char[] lowercaseLettersAlphabet = {'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char aNumber : numbersAlphabet) {
            for (char aLowercaseLetter : lowercaseLettersAlphabet) {
                String code = inviteCode.replace("*", String.valueOf(aNumber)).replace("#", String.valueOf(aLowercaseLetter));
                JSONObject req = new JSONObject();
                req.put("action", "invite_code");
                req.put("code", code);
                String response = HttpClientUtils.post(clCheckInviteCodeUrl, req.toJSONString());
                System.out.println(response);
            }
        }
    }

}
