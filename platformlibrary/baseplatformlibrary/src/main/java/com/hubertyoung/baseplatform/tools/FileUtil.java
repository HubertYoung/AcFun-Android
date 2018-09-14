//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hubertyoung.baseplatform.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2015/10/11
 */
public class FileUtil {

    public static void copyFile( File srcFile, File destFile) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("Source must not be null");
        } else if (destFile == null) {
            throw new NullPointerException("Destination must not be null");
        } else if (!srcFile.exists()) {
            throw new FileNotFoundException("Source \'" + srcFile + "\' does not exist");
        } else if (srcFile.isDirectory()) {
            throw new IOException("Source \'" + srcFile + "\' exists but is a directory");
        } else if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("Source \'" + srcFile + "\' and destination \'" + destFile + "\' are the same");
        } else {
            File parentFile = destFile.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination \'" + parentFile + "\' directory cannot be created");
            } else if (destFile.exists() && !destFile.canWrite()) {
                throw new IOException("Destination \'" + destFile + "\' exists but is read-only");
            } else {
                doCopyFile(srcFile, destFile);
            }
        }
    }

    private static void doCopyFile( File srcFile, File destFile) throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException("Destination \'" + destFile + "\' exists but is a directory");
        } else {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel input = null;
            FileChannel output = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                input = fis.getChannel();
                output = fos.getChannel();
                long size = input.size();
                long pos = 0L;

                for (long count = 0L; pos < size; pos += output.transferFrom(input, pos, count)) {
                    count = size - pos > 31457280L ? 31457280L : size - pos;
                }
            } finally {
                IOUtil.closeQuietly(output);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(input);
                IOUtil.closeQuietly(fis);
            }

            if (srcFile.length() != destFile.length()) {
                throw new IOException("Failed to copy full contents from \'" + srcFile + "\' to \'" + destFile + "\'");
            }
        }
    }

}
