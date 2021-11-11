package com.github.caijh.commons.util

import org.junit.Assert.assertNotNull
import org.junit.Test

class CertificateUtilsTest {

    @Test
    fun getKeyStore() {
        val keyStore = CertificateUtils.getKeyStore("src/test/resources/keystore.jks", "123456")
        assertNotNull(keyStore)
    }

    @Test
    fun getPrivateKey() {
        val privateKey = CertificateUtils.getPrivateKey("src/test/resources/keystore.jks", "123456", "test")
        assertNotNull(privateKey)
    }

    @Test
    fun getPublicKey() {
        val certificate = CertificateUtils.getCertificate("src/test/resources/keystore.jks", "123456", "test")

        val publicKey = certificate?.publicKey
        println(publicKey)
        assertNotNull(publicKey)
    }

    @Test
    fun getSigAlgName() {
        val sigAlgName = CertificateUtils.getSigAlgName("src/test/resources/keystore.jks", "123456", "test")
        println(sigAlgName)
        assertNotNull(sigAlgName)
    }

}
