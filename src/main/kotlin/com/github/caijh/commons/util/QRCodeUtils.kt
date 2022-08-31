package com.github.caijh.commons.util

import com.github.caijh.commons.image.Images
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.io.ByteArrayOutputStream
import javax.annotation.Nonnull
import javax.imageio.ImageIO

object QRCodeUtils {
    @JvmStatic
    fun createQRCode(@Nonnull content: String, @Nonnull width: Int, @Nonnull height: Int): String {
        val writer = QRCodeWriter()
        val os = ByteArrayOutputStream()
        val hints = HashMap<EncodeHintType, Comparable<*>>()
        hints[EncodeHintType.CHARACTER_SET] = "utf-8" // 指定字符编码为“utf-8”
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H // 指定二维码的纠错等级为中级
        hints[EncodeHintType.MARGIN] = 2 // 设置图片的边距
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints)
        val bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix)
        ImageIO.write(bufferedImage, "png", os)
        return Images.appendImageSchema(Base64Utils.encrypt(os.toByteArray()))
    }
}
