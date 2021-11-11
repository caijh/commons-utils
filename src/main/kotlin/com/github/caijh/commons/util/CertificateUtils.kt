package com.github.caijh.commons.util

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*

object CertificateUtils {

    /** Java密钥库(Java 密钥库，JKS)KEY_STORE  */
    private const val KEY_STORE = "JKS"

    private const val X509 = "X.509"

    @JvmStatic
    fun getKeyStore(keyStorePath: String, password: String): KeyStore {
        try {
            FileInputStream(keyStorePath).use { `in` ->
                val keyStore =
                    KeyStore.getInstance(KEY_STORE)
                keyStore.load(`in`, password.toCharArray())
                return keyStore
            }
        } catch (e: FileNotFoundException) {
            throw CertificateException("密钥库文件 [$keyStorePath] 不存在", e)
        } catch (e: Exception) {
            throw CertificateException("读取密钥库文件 [$keyStorePath] 失败, password is $password", e)
        }
    }

    /**
     * 根据证书获取私钥
     */
    @JvmStatic
    fun getPrivateKey(keyStorePath: String, password: String, alias: String): PrivateKey? {
        val keyStore: KeyStore = getKeyStore(keyStorePath, password)
        return try {
            keyStore.getKey(alias, password.toCharArray()) as PrivateKey
        } catch (e: java.lang.Exception) {
            throw CertificateException("读取PrivateKey失败", e)
        }
    }


    /**
     * 获得证书对象
     *
     * @param certificatePath 证书存储路径
     * @return 证书
     */
    @JvmStatic
    fun getCertificate(certificatePath: String): Certificate {
        return try {
            val certificateFactory = CertificateFactory.getInstance(X509)
            val inStream = FileInputStream(certificatePath)
            certificateFactory.generateCertificate(inStream)
        } catch (e: FileNotFoundException) {
            throw CertificateException("证书文件 [$certificatePath] 不存在", e)
        } catch (e: java.lang.Exception) {
            throw CertificateException("加载证书 [$certificatePath] 失败", e)
        }
    }


    /**
     * 根据密钥库获得证书
     *
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     * @return 证书
     */
    @JvmStatic
    fun getCertificate(keyStorePath: String, password: String, alias: String): Certificate? {
        val keyStore: KeyStore = getKeyStore(keyStorePath, password)
        return try {
            keyStore.getCertificate(alias)
        } catch (e: KeyStoreException) {
            throw CertificateException(
                "根据密钥库加载证书失败keyStorePath = $keyStorePath, password = $password, alias = $alias",
                e
            )
        }
    }

    /**
     * 根据证书获得公钥
     *
     * @param certificatePath 证书存储路径
     * @return 公钥
     */
    @JvmStatic
    fun getPublicKey(certificatePath: String): PublicKey {
        val certificate: Certificate = getCertificate(certificatePath)
        return certificate.publicKey
    }


    /**
     * 根据密钥库获取其签名算法
     *
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     * @return 签名算法的名称
     */
    fun getSigAlgName(keyStorePath: String, password: String, alias: String): String? {
        // 获得证书
        val certificate: Certificate =
            getCertificate(keyStorePath, password, alias) as? X509Certificate
                ?: throw CertificateException("密钥库对应 [$keyStorePath] 的证书类型不是 X.509，无法读取该密钥库对应的签名算法。")
        return (certificate as X509Certificate).sigAlgName
    }


    /**
     * 验证证书是否过期或无效
     *
     * @param date 日期
     * @param certificate 证书
     * @return 有效则返回 `true`，否则返回 `false`
     */
    @JvmStatic
    fun verifyCertificate(certificate: X509Certificate, date: Date?): Boolean {
        return try {
            certificate.checkValidity(date ?: Date())
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

}
