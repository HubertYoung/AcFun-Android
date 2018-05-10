package com.hubertyoung.common.utils;

import android.annotation.SuppressLint;

import com.hubertyoung.common.constant.MemoryConstants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * Created by ${lei} on 2017/7/15.
 * Desc: 文件工具类
 * Since：
 */

public class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException( "u can't instantiate me..." );
    }

    private static final String LINE_SEP = File.separator;

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath( String filePath ) {
        return isSpace( filePath ) ? null : new File( filePath );
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists( String filePath ) {
        return isFileExists( getFileByPath( filePath ) );
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists( File file ) {
        return file != null && file.exists();
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir( String dirPath ) {
        return isDir( getFileByPath( dirPath ) );
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir( File file ) {
        return isFileExists( file ) && file.isDirectory();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile( String filePath ) {
        return isFile( getFileByPath( filePath ) );
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile( File file ) {
        return isFileExists( file ) && file.isFile();
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir( String dirPath ) {
        return createOrExistsDir( getFileByPath( dirPath ) );
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir( File file ) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && ( file.exists() ? file.isDirectory() : file.mkdirs() );
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile( String filePath ) {
        return createOrExistsFile( getFileByPath( filePath ) );
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile( File file ) {
        if ( file == null ) return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if ( file.exists() ) return file.isFile();
        if ( !createOrExistsDir( file.getParentFile() ) ) return false;
        try {
            return file.createNewFile();
        } catch ( IOException e ) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param filePath 文件路径
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile( String filePath ) {
        return createFileByDeleteOldFile( getFileByPath( filePath ) );
    }

    /**
     * 判断文件是否存在，存在则在创建之前删除
     *
     * @param file 文件
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile( File file ) {
        if ( file == null ) return false;
        // 文件存在并且删除失败返回false
        if ( file.exists() && file.isFile() && !file.delete() ) return false;
        // 创建目录失败返回false
        if ( !createOrExistsDir( file.getParentFile() ) ) return false;
        try {
            return file.createNewFile();
        } catch ( IOException e ) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir( String dirPath ) {
        return deleteDir( getFileByPath( dirPath ) );
    }

    /**
     * 删除目录
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir( File dir ) {
        if ( dir == null ) return false;
        // 目录不存在返回true
        if ( !dir.exists() ) return true;
        // 不是目录返回false
        if ( !dir.isDirectory() ) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if ( files != null && files.length != 0 ) {
            for (File file : files) {
                if ( file.isFile() ) {
                    if ( !deleteFile( file ) ) return false;
                } else if ( file.isDirectory() ) {
                    if ( !deleteDir( file ) ) return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 删除文件
     *
     * @param srcFilePath 文件路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile( String srcFilePath ) {
        return deleteFile( getFileByPath( srcFilePath ) );
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile( File file ) {
        return file != null && ( !file.exists() || file.isFile() && file.delete() );
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir( String dirPath ) {
        return deleteFilesInDir( getFileByPath( dirPath ) );
    }

    /**
     * 删除目录下的所有文件
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir( File dir ) {
        if ( dir == null ) return false;
        // 目录不存在返回true
        if ( !dir.exists() ) return true;
        // 不是目录返回false
        if ( !dir.isDirectory() ) return false;
        // 现在文件存在且是文件夹
        File[] files = dir.listFiles();
        if ( files != null && files.length != 0 ) {
            for (File file : files) {
                if ( file.isFile() ) {
                    if ( !deleteFile( file ) ) return false;
                } else if ( file.isDirectory() ) {
                    if ( !deleteDir( file ) ) return false;
                }
            }
        }
        return true;
    }

    /**
     * 将输入流写入文件
     *
     * @param filePath 路径
     * @param is       输入流
     * @param append   是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromIS( String filePath, InputStream is, boolean append ) {
        return writeFileFromIS( getFileByPath( filePath ), is, append );
    }

    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromIS( File file, InputStream is, boolean append ) {
        if ( file == null || is == null ) return false;
        if ( !createOrExistsFile( file ) ) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream( new FileOutputStream( file, append ) );
            byte data[] = new byte[ 1024 ];
            int len;
            while ( ( len = is.read( data, 0, 1024 ) ) != -1 ) {
                os.write( data, 0, len );
            }
            return true;
        } catch ( IOException e ) {
            CommonLog.loge( e.getMessage().toString() );
            return false;
        } finally {
            try {
                if ( is != null ) {
                    is.close();
                }
                if ( os != null ) {
                    os.close();
                }
            } catch ( IOException e ) {
                CommonLog.loge( e.getMessage().toString() );
                return false;
            }
        }
    }

    /**
     * 简单获取文件编码格式
     *
     * @param filePath 文件路径
     * @return 文件编码
     */
    public static String getFileCharsetSimple( String filePath ) {
        return getFileCharsetSimple( getFileByPath( filePath ) );
    }

    /**
     * 简单获取文件编码格式
     *
     * @param file 文件
     * @return 文件编码
     */
    public static String getFileCharsetSimple( File file ) {
        int p = 0;
        InputStream is = null;
        try {
            is = new BufferedInputStream( new FileInputStream( file ) );
            p = ( is.read() << 8 ) + is.read();
        } catch ( IOException e ) {
            CommonLog.loge( e.getMessage().toString() );
            return "";
        } finally {
            try {
                if ( is != null ) {
                    is.close();
                }
            } catch ( IOException e ) {
                CommonLog.loge( e.getMessage().toString() );
                return null;
            }
        }
        switch ( p ) {
            case 0xefbb:
                return "UTF-8";
            case 0xfffe:
                return "Unicode";
            case 0xfeff:
                return "UTF-16BE";
            default:
                return "GBK";
        }
    }

    /**
     * 获取目录大小
     *
     * @param dirPath 目录路径
     * @return 文件大小
     */
    public static String getDirSize( String dirPath ) {
        return getDirSize( getFileByPath( dirPath ) );
    }

    /**
     * 获取目录大小
     *
     * @param dir 目录
     * @return 文件大小
     */
    public static String getDirSize( File dir ) {
        long len = getDirLength( dir );
        return len == -1 ? "" : byte2FitMemorySize( len );
    }
    /**
     * 获取目录大小
     *
     * @param dir 目录
     * @return 文件大小
     */
    public static long getDirSize( File dir , boolean isNum) {
        long len = getDirLength( dir );
        return len;
    }
    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static String getFileSize( String filePath ) {
        return getFileSize( getFileByPath( filePath ) );
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小
     */
    public static String getFileSize( File file ) {
        long len = getFileLength( file );
        return len == -1 ? "" : byte2FitMemorySize( len );
    }

    /**
     * 获取目录长度
     *
     * @param dirPath 目录路径
     * @return 目录长度
     */
    public static long getDirLength( String dirPath ) {
        return getDirLength( getFileByPath( dirPath ) );
    }

    /**
     * 获取目录长度
     *
     * @param dir 目录
     * @return 目录长度
     */
    public static long getDirLength( File dir ) {
        if ( !isDir( dir ) ) return -1;
        long len = 0;
        File[] files = dir.listFiles();
        if ( files != null && files.length != 0 ) {
            for (File file : files) {
                if ( file.isDirectory() ) {
                    len += getDirLength( file );
                } else {
                    len += file.length();
                }
            }
        }
        return len;
    }

    /**
     * 获取文件长度
     *
     * @param filePath 文件路径
     * @return 文件长度
     */
    public static long getFileLength( String filePath ) {
        return getFileLength( getFileByPath( filePath ) );
    }

    /**
     * 获取文件长度
     *
     * @param file 文件
     * @return 文件长度
     */
    public static long getFileLength( File file ) {
        if ( !isFile( file ) ) return -1;
        return file.length();
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static String getFileMD5ToString( String filePath ) {
        File file = isSpace( filePath ) ? null : new File( filePath );
        return getFileMD5ToString( file );
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static byte[] getFileMD5( String filePath ) {
        File file = isSpace( filePath ) ? null : new File( filePath );
        return getFileMD5( file );
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static String getFileMD5ToString( File file ) {
        return bytes2HexString( getFileMD5( file ) );
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static byte[] getFileMD5( File file ) {
        if ( file == null ) return null;
        DigestInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream( file );
            MessageDigest md = MessageDigest.getInstance( "MD5Utils" );
            dis = new DigestInputStream( fis, md );
            byte[] buffer = new byte[ 1024 * 256 ];
            //noinspection StatementWithEmptyBody
            while ( dis.read( buffer ) > 0 ) ;
            md = dis.getMessageDigest();
            return md.digest();
        } catch ( NoSuchAlgorithmException | IOException e ) {
            CommonLog.loge( e.getMessage().toString() );
        } finally {
            try {
                if ( dis != null ) {
                    dis.close();
                }
            } catch ( IOException e ) {
                CommonLog.loge( e.getMessage().toString() );
                return null;
            }
        }
        return null;
    }

    /**
     * 获取全路径中的文件名
     *
     * @param file 文件
     * @return 文件名
     */
    public static String getFileName( File file ) {
        if ( file == null ) return null;
        return getFileName( file.getPath() );
    }

    /**
     * 获取全路径中的文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName( String filePath ) {
        if ( isSpace( filePath ) ) return filePath;
        int lastSep = filePath.lastIndexOf( File.separator );
        return lastSep == -1 ? filePath : filePath.substring( lastSep + 1 );
    }

    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param file 文件
     * @return 不带拓展名的文件名
     */
    public static String getFileNameNoExtension( File file ) {
        if ( file == null ) return null;
        return getFileNameNoExtension( file.getPath() );
    }

    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param filePath 文件路径
     * @return 不带拓展名的文件名
     */
    public static String getFileNameNoExtension( String filePath ) {
        if ( isSpace( filePath ) ) return filePath;
        int lastPoi = filePath.lastIndexOf( '.' );
        int lastSep = filePath.lastIndexOf( File.separator );
        if ( lastSep == -1 ) {
            return ( lastPoi == -1 ? filePath : filePath.substring( 0, lastPoi ) );
        }
        if ( lastPoi == -1 || lastSep > lastPoi ) {
            return filePath.substring( lastSep + 1 );
        }
        return filePath.substring( lastSep + 1, lastPoi );
    }

    /**
     * 获取全路径中的文件拓展名
     *
     * @param file 文件
     * @return 文件拓展名
     */
    public static String getFileExtension( File file ) {
        if ( file == null ) return null;
        return getFileExtension( file.getPath() );
    }

    /**
     * 获取全路径中的文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    public static String getFileExtension( String filePath ) {
        if ( isSpace( filePath ) ) return filePath;
        int lastPoi = filePath.lastIndexOf( '.' );
        int lastSep = filePath.lastIndexOf( File.separator );
        if ( lastPoi == -1 || lastSep >= lastPoi ) return "";
        return filePath.substring( lastPoi + 1 );
    }

    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * byteArr转hexString
     * <p>例如：</p>
     * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
     *
     * @param bytes 字节数组
     * @return 16进制大写字符串
     */
    private static String bytes2HexString( byte[] bytes ) {
        if ( bytes == null ) return null;
        int len = bytes.length;
        if ( len <= 0 ) return null;
        char[] ret = new char[ len << 1 ];
        for (int i = 0, j = 0; i < len; i++) {
            ret[ j++ ] = hexDigits[ bytes[ i ] >>> 4 & 0x0f ];
            ret[ j++ ] = hexDigits[ bytes[ i ] & 0x0f ];
        }
        return new String( ret );
    }

    /**
     * 字节数转合适内存大小
     * <p>保留3位小数</p>
     *
     * @param byteNum 字节数
     * @return 合适内存大小
     */
    @SuppressLint( "DefaultLocale" )
    public static String byte2FitMemorySize( long byteNum ) {
        if ( byteNum < 0 ) {
            return "0KB";
        } else if ( byteNum < MemoryConstants.KB ) {
            return String.format( "%.2fB", ( double ) byteNum + 0.0005 );
        } else if ( byteNum < MemoryConstants.MB ) {
            return String.format( "%.2fKB", ( double ) byteNum / MemoryConstants.KB + 0.0005 );
        } else if ( byteNum < MemoryConstants.GB ) {
            return String.format( "%.2fMB", ( double ) byteNum / MemoryConstants.MB + 0.0005 );
        } else {
            return String.format( "%.2fGB", ( double ) byteNum / MemoryConstants.GB + 0.0005 );
        }
    }

    private static boolean isSpace( String s ) {
        if ( s == null ) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if ( !Character.isWhitespace( s.charAt( i ) ) ) {
                return false;
            }
        }
        return true;
    }

}
