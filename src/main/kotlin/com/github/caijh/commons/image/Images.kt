package com.github.caijh.commons.image

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
    fun getScaleImage(`in`: InputStream, scale: Float, format: String, out: OutputStream) {
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

    @JvmStatic
    fun getSubImage(
        inStream: InputStream,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        formatName: String,
        outStream: OutputStream
    ) {
        val bufferedImage = ImageIO.read(inStream)
        val image: BufferedImage = bufferedImage.getSubimage(x, y, width, height)
        ImageIO.write(image, formatName, outStream)
    }
}
