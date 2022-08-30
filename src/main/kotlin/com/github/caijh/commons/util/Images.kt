package com.github.caijh.commons.util

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.InputStream
import java.io.OutputStream
import javax.imageio.ImageIO

object Images {

    @JvmStatic
    fun appendImageSchema(base64Str: String): String {
        return "data:image/png;base64,${base64Str}"
    }

    @JvmStatic
    fun scaleImage(`in`: InputStream, scale: Float, format: String, out: OutputStream) {
        val read: BufferedImage = ImageIO.read(`in`)
        val width: Int = (read.width * scale).toInt()
        val height: Int = (read.height * scale).toInt()
        val img = read.getScaledInstance(width, height, Image.SCALE_DEFAULT)
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        graphics.drawImage(img, 0, 0, null)
        graphics.dispose()
        ImageIO.write(image, format, out)
    }
}
