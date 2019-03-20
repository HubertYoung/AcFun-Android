package com.hubertyoung.common.utils.file;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.constant.AppConfig;
import com.hubertyoung.common.constant.MemoryConstants;

import java.io.File;


/**
 * @author:Yang
 * @date:2017/7/17 12:06
 * @since:v1.0
 * @desc:com.hubertyoung.common.commonutils
 * @param:
 */
public class AppFileUtils {

	/**
	 * 检查SD卡是否存在
	 */
	public static boolean checkSdCard() {

		return Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED );
	}

	/**
	 * 录制视频的目录
	 *
	 * @return
	 */
	public static String getRecoderVideosDir() {
		return makeDir( getAppDir(), AppConfig.APP_RECODER_VIDEO_PATH );
	}

	/**
	 * 录制视频第一帧图片的目录
	 *
	 * @return
	 */
	public static String getRecoderVideoFirstFramDir() {
		return makeDir( getAppDir(), AppConfig.APP_RECODER_VIDEO_FIRST_FRAME_PATH );
	}

	/**
	 * 获取项目下载管理目录
	 *
	 * @return 绝对路径
	 */
	public static String getDownDir() {
		return makeDir( getAppDir(), AppConfig.APP_DOWN_PATH );
	}

	/**
	 * 获取项目日志管理目录
	 *
	 * @return 绝对路径
	 */
	public static String getLogDir() {
		return makeDir( getAppDir(), AppConfig.APP_LOG_PATH );
	}

	/**
	 * 获取项目换肤管理目录
	 *
	 * @return 绝对路径
	 */
	public static String getSkinsDir() {
		return makeDir( getAppDir(), AppConfig.APP_SKINS_PATH );
	}

	/**
	 * 获取项目图片管理目录
	 *
	 * @return 绝对路径
	 */
	public static String getImgDir() {
		return makeDir( getAppDir(), AppConfig.APP_IMAGE_PATH );
	}
	/**
	 * 获取项目插件管理目录
	 *
	 * @return 绝对路径
	 */
	public static String getPhantomDir() {
		return makeDir( getAppDir(), AppConfig.APP_PHANTOM_PATH );
	}

	/**
	 * 获取项目缓存目录
	 *
	 * @return 绝对路径
	 */
	public static String getAppCacheDir() {
		return makeDir( getAppDir(), AppConfig.APP_CACHE_PATH );
	}

	/**
	 * 获取项目管理目录
	 *
	 * @return 存在sd卡则在包名下创建文件夹 、如果不存在sd卡 则在手机缓存目录创建文件夹
	 */
	public static String getAppDir() {
		return makeDir( getAppAbsoluteDir(), AppConfig.APP_DEPLOY_PATH );
	}

	/**
	 * 获取缓存根目录
	 *
	 * @return 缓存绝对路径
	 */
	public static String getAppAbsoluteDir() {
		if ( Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED ) ) {
			File cacheDir = CommonApplication.getAppContext().getExternalCacheDir();
			if ( cacheDir != null && ( cacheDir.exists() || cacheDir.mkdirs() ) ) {
				return cacheDir.getAbsolutePath();
			}
		}
		return CommonApplication.getAppContext().getCacheDir().getAbsolutePath();
	}

	public static String getFileFromUri( Uri uri ) {
		if ( CommonApplication.getAppContext() == null || uri == null ) return null;
		if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri( CommonApplication.getAppContext(), uri ) ) {
			if ( isExternalStorageDocument( uri ) ) {
				String docId = DocumentsContract.getDocumentId( uri );
				String[] split = docId.split( ":" );
				String type = split[ 0 ];
				if ( "primary".equalsIgnoreCase( type ) ) {
					return Environment.getExternalStorageDirectory() + "/" + split[ 1 ];
				}
			} else if ( isDownloadsDocument( uri ) ) {
				String id = DocumentsContract.getDocumentId( uri );
				Uri contentUri = ContentUris.withAppendedId( Uri.parse( "content://downloads/public_downloads" ), Long.valueOf( id ) );
				return getDataColumn( CommonApplication.getAppContext(), contentUri, null, null );
			} else if ( isMediaDocument( uri ) ) {
				String docId = DocumentsContract.getDocumentId( uri );
				String[] split = docId.split( ":" );
				String type = split[ 0 ];
				Uri contentUri = null;
				if ( "image".equals( type ) ) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ( "video".equals( type ) ) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ( "audio".equals( type ) ) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[]{ split[ 1 ] };
				return getDataColumn( CommonApplication.getAppContext(), contentUri, selection, selectionArgs );
			}
		} // MediaStore (and general)
		else if ( "content".equalsIgnoreCase( uri.getScheme() ) ) {
			// Return the remote address
			if ( isGooglePhotosUri( uri ) ) return uri.getLastPathSegment();
			return getDataColumn( CommonApplication.getAppContext(), uri, null, null );
		}
		// File
		else if ( "file".equalsIgnoreCase( uri.getScheme() ) ) {
			return uri.getPath();
		}
		return null;
	}

	static String getDataColumn( Context context, Uri uri, String selection, String[] selectionArgs ) {
		Cursor cursor = null;
		String[] projection = { MediaStore.Images.Media.DATA };
		try {
			cursor = context.getContentResolver().query( uri, projection, selection, selectionArgs, null );
			if ( cursor != null && cursor.moveToFirst() ) {
				int index = cursor.getColumnIndexOrThrow( MediaStore.Images.Media.DATA );
				return cursor.getString( index );
			}
		} finally {
			if ( cursor != null ) cursor.close();
		}
		return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	static boolean isExternalStorageDocument( Uri uri ) {
		return "com.android.externalstorage.documents".equals( uri.getAuthority() );
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	static boolean isDownloadsDocument( Uri uri ) {
		return "com.android.providers.downloads.documents".equals( uri.getAuthority() );
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	static boolean isMediaDocument( Uri uri ) {
		return "com.android.providers.media.documents".equals( uri.getAuthority() );
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	static boolean isGooglePhotosUri( Uri uri ) {
		return "com.google.android.apps.photos.content".equals( uri.getAuthority() );
	}


	/**
	 * 创建文件夹
	 *
	 * @param appDir2
	 * @param appDeployPath
	 * @return
	 */
	@NonNull
	public static String makeDir( String appDir2, String appDeployPath ) {
		File appDir = new File( appDir2, appDeployPath );
		if ( !appDir.exists() ) {
			appDir.mkdirs();
		}
		return appDir.getAbsolutePath();
	}

	/**
	 * 查看SD卡空闲大小
	 *
	 * @return
	 */
	public static String getSDAvailableSize() {
		if ( checkSdCard() ) {
			//取得SD卡文件路径
			File path = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs( path.getPath() );
			//获取单个数据块的大小(Byte)
			long blockSize = sf.getBlockSize();
			//空闲的数据块的数量
			long freeBlocks = sf.getAvailableBlocks();
			//返回SD卡空闲大小
			return MemoryConstants.getFormatSize( freeBlocks * blockSize );
		} else {
			return "0";
		}
	}


	/**
	 * 查看SD卡总容量
	 *
	 * @return 对应单位大小
	 */
	public static String getSDTotalSize() {
		if ( checkSdCard() ) {
			//取得SD卡文件路径
			File path = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs( path.getPath() );
			//获取单个数据块的大小(Byte)
			long blockSize = sf.getBlockSize();
			//获取所有数据块数
			long allBlocks = sf.getBlockCount();
			//返回SD卡大小
			//return (allBlocks * blockSize)/1024; //单位KB
			return MemoryConstants.getFormatSize( allBlocks * blockSize ); //单位MB
		} else {
			return "0";
		}
	}

	/**
	 * 获取手机内部存储总空间
	 *
	 * @return
	 */
	public static String getPhoneTotalSize() {

		if ( !checkSdCard() ) {
			File path = Environment.getDataDirectory();
			StatFs mStatFs = new StatFs( path.getPath() );
			long blockSizeLong = mStatFs.getBlockSize();
			long blockCountLong = mStatFs.getBlockCount();
			return MemoryConstants.getFormatSize( blockSizeLong * blockCountLong );
		} else {
			return getSDTotalSize();
		}
	}

	/**
	 * 获取手机内存存储可用空间
	 *
	 * @return
	 */
	public static String getPhoneAvailableSize() {

		if ( !checkSdCard() ) {
			File path = Environment.getDataDirectory();
			StatFs mStatFs = new StatFs( path.getPath() );
			long blockSizeLong = mStatFs.getBlockSize();
			long blockCountLong = mStatFs.getBlockCount();
			return MemoryConstants.getFormatSize( blockSizeLong * blockCountLong );
		} else return getSDAvailableSize();
	}
}
