package com.github.caijh.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="caiqizhe@gmail.com">caiqizhe@gmail.com</a>
 * @since 2016-11-17
 */
public class CheckSumUtils {

    private CheckSumUtils() {
    }

    /**
     * 获取文件的sum值.
     *
     * @param file the file
     * @return the sum value string of file
     * @throws NoSuchAlgorithmException SHA1 algorithm not found
     * @throws IOException              if read file fail
     */
    public static String checkSum(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA1");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] dataBytes = new byte[1024];

            int bytesRead;

            while ((bytesRead = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, bytesRead);
            }

            byte[] mdBytes = md.digest();

            // convert the byte to hex format
            StringBuilder sb = new StringBuilder();
            for (byte mdByte : mdBytes) {
                sb.append(Integer.toString((mdByte & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
    }

}
