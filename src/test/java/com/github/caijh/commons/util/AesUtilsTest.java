package com.github.caijh.commons.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Assert;
import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;


public class AesUtilsTest {

    @Test
    public void test() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        String a = "test";
        AesUtils.AesKey aesKey = AesUtils.initAesKey();
        byte[] encrypt = AesUtils.encrypt(a.getBytes(UTF_8), aesKey);
        Assert.assertEquals(a, new String(AesUtils.decrypt(encrypt, aesKey), UTF_8));
    }

}
