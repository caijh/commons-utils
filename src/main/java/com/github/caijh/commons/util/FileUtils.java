package com.github.caijh.commons.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * FileUtils.
 *
 * @author caijunhui 2017/11/11
 **/
public class FileUtils {

    private FileUtils() {
    }

    public static File createFile(String pathname) throws IOException {
        File file = new File(pathname);
        if (!file.exists()) {
            boolean ret = file.createNewFile();
            if (!ret) {
                throw new IOException("create new file fail");
            }
        }
        return file;
    }

    public static void package2Zip(String zipFileName, List<File> zipEntryFiles) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (File file : zipEntryFiles) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zout.putNextEntry(zipEntry);
                FileInputStream fin = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                while (fin.read(bytes) > -1) {
                    zout.write(bytes);
                }
                zout.closeEntry();
            }
        }
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ignored) {
        }
    }

    public static boolean delete(File file) {
        return file.delete();
    }

    public static void copy(File src, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(src);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (Exception ignored) {
            // ignore
        } finally {
            FileUtils.close(in);
            FileUtils.close(out);
            FileUtils.close(inStream);
            FileUtils.close(outStream);
        }
    }

}
