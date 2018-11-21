package com.hubertyoung.common.utils.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.utils.file.FileUtils;
import com.hubertyoung.common.utils.log.CommonLog;

import java.io.File;

import androidx.core.content.FileProvider;


/**
 * @author:Yang
 * @date:2017/8/2 15:57
 * @since:v1.0
 * @desc:IntentUtils.java
 * @param:意图相关工具类
 */
public final class IntentUtils {

	private IntentUtils() {
		throw new UnsupportedOperationException( "u can't fuck me..." );
	}

	/**
	 * 获取安装App（支持7.0）的意图
	 *
	 * @param filePath  文件路径
	 * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 * @return intent
	 */
	public static Intent getInstallAppIntent( final String filePath, final String authority ) {
		return getInstallAppIntent( FileUtils.getFileByPath( filePath ), authority );
	}

	/**
	 * 获取安装App(支持7.0)的意图
	 *
	 * @param file      文件
	 * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
	 *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
	 * @return intent
	 */
	public static Intent getInstallAppIntent( final File file, final String authority ) {
		if ( file == null ) return null;
		Intent intent = new Intent( Intent.ACTION_VIEW );
		Uri data;
		String type = "application/vnd.android.package-archive";
		if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.N ) {
			data = Uri.fromFile( file );
		} else {
			intent.setFlags( Intent.FLAG_GRANT_READ_URI_PERMISSION );
			data = FileProvider.getUriForFile( CommonApplication.getAppContext(), authority, file );
		}
		intent.setDataAndType( data, type );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取卸载App的意图
	 *
	 * @param packageName 包名
	 * @return intent
	 */
	public static Intent getUninstallAppIntent( final String packageName ) {
		Intent intent = new Intent( Intent.ACTION_DELETE );
		intent.setData( Uri.parse( "package:" + packageName ) );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取打开App的意图
	 *
	 * @param packageName 包名
	 * @return intent
	 */
	public static Intent getLaunchAppIntent( final String packageName ) {
		return CommonApplication.getAppContext()
							  .getPackageManager()
							  .getLaunchIntentForPackage( packageName );
	}

	/**
	 * 获取App具体设置的意图
	 *
	 * @param packageName 包名
	 * @return intent
	 */
	public static Intent getAppDetailsSettingsIntent( final String packageName ) {
		Intent intent = new Intent( "android.settings.APPLICATION_DETAILS_SETTINGS" );
		intent.setData( Uri.parse( "package:" + packageName ) );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取分享文本的意图
	 *
	 * @param content 分享文本
	 * @return intent
	 */
	public static Intent getShareTextIntent( final String content ) {
		Intent intent = new Intent( Intent.ACTION_SEND );
		intent.setType( "text/plain" );
		intent.putExtra( Intent.EXTRA_TEXT, content );
		return intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取分享图片的意图
	 *
	 * @param content   文本
	 * @param imagePath 图片文件路径
	 * @return intent
	 */
	public static Intent getShareImageIntent( final String content, final String imagePath ) {
		return getShareImageIntent( content, FileUtils.getFileByPath( imagePath ) );
	}

	/**
	 * 获取分享图片的意图
	 *
	 * @param content 文本
	 * @param image   图片文件
	 * @return intent
	 */
	public static Intent getShareImageIntent( final String content, final File image ) {
		if ( !FileUtils.isFileExists( image ) ) return null;
		return getShareImageIntent( content, Uri.fromFile( image ) );
	}

	/**
	 * 获取分享图片的意图
	 *
	 * @param content 分享文本
	 * @param uri     图片uri
	 * @return intent
	 */
	public static Intent getShareImageIntent( final String content, final Uri uri ) {
		Intent intent = new Intent( Intent.ACTION_SEND );
		intent.putExtra( Intent.EXTRA_TEXT, content );
		intent.putExtra( Intent.EXTRA_STREAM, uri );
		intent.setType( "image/*" );
		return intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取其他应用组件的意图
	 *
	 * @param packageName 包名
	 * @param className   全类名
	 * @return intent
	 */
	public static Intent getComponentIntent( final String packageName, final String className ) {
		return getComponentIntent( packageName, className, null );
	}

	/**
	 * 获取其他应用组件的意图
	 *
	 * @param packageName 包名
	 * @param className   全类名
	 * @param bundle      bundle
	 * @return intent
	 */
	public static Intent getComponentIntent( final String packageName, final String className, final Bundle bundle ) {
		Intent intent = new Intent( Intent.ACTION_VIEW );
		if ( bundle != null ) intent.putExtras( bundle );
		ComponentName cn = new ComponentName( packageName, className );
		intent.setComponent( cn );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取关机的意图
	 * <p>需添加权限 {@code <uses-permission android:name="android.permission.SHUTDOWN"/>}</p>
	 *
	 * @return intent
	 */
	public static Intent getShutdownIntent() {
		Intent intent = new Intent( Intent.ACTION_SHUTDOWN );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取跳至拨号界面意图
	 *
	 * @param phoneNumber 电话号码
	 */
	public static Intent getDialIntent( final String phoneNumber ) {
		Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + phoneNumber ) );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取拨打电话意图
	 * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
	 *
	 * @param phoneNumber 电话号码
	 */
	public static Intent getCallIntent( final String phoneNumber ) {
		Intent intent = new Intent( "android.intent.action.CALL", Uri.parse( "tel:" + phoneNumber ) );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	/**
	 * 获取跳至发送短信界面的意图
	 *
	 * @param phoneNumber 接收号码
	 * @param content     短信内容
	 */
	public static Intent getSendSmsIntent( final String phoneNumber, final String content ) {
		Uri uri = Uri.parse( "smsto:" + phoneNumber );
		Intent intent = new Intent( Intent.ACTION_SENDTO, uri );
		intent.putExtra( "sms_body", content );
		return intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
	}


	/**
	 * 获取拍照的意图
	 *
	 * @param outUri 输出的uri
	 * @return 拍照的意图
	 */
	public static Intent getCaptureIntent( final Uri outUri ) {
		Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
		intent.putExtra( MediaStore.EXTRA_OUTPUT, outUri );
		return intent.addFlags( Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK );
	}

	public static void startActivity( Activity activity, int action, String url, Bundle bundle) {
		if (url != null) {
			try {
				Intent intent = new Intent();
				switch (action) {
					case 1:
//						IntentHelper.a(activity, Integer.valueOf(str).intValue(), "recommend");
						break;
					case 2:
//						IntentHelper.c(activity, Integer.valueOf(str).intValue(), "recommend");
						break;
					case 3:
//						User user = new User();
//						user.setUid(Integer.valueOf(str).intValue());
//						IntentHelper.a(activity, user);
						break;
					case 4:
						if (bundle == null) {
							bundle = new Bundle();
						}
						bundle.putString("url", url);
//						IntentHelper.a(activity, WebViewActivity.class, bundle);
						break;
					case 5:
					case 18:
						Uri parse = Uri.parse(url);
//						intent.setAction(ACTION.HWID_SCHEME_URL);
						intent.setData(parse);
						break;
					case 6:
//						IntentHelper.b(activity, Integer.valueOf(str).intValue());
						break;
					case 7:
//						intent.setClass(activity, RankActivity.class);
//						if (!TextUtils.isEmpty(str)) {
//							intent.putExtra(RankActivity.f, -1);
//							intent.putExtra(RankActivity.e, Integer.parseInt(str));
//							break;
//						}
//						intent.putExtra(RankActivity.f, 2);
						break;
					case 8:
//						intent.setClass(activity, SerialBangumiActivity.class);
//						intent.putExtra("category", BangumiType.ANIMATION);
						break;
					case 9:
						break;
					case 10:
//						IntentHelper.b(activity, Integer.valueOf(str).intValue(), "recommend");
						break;
					case 11:
//						intent.setClass(activity, RankActivity.class);
//						intent.putExtra(RankActivity.f, 1);
						break;
					case 12:
//						IntentHelper.a(activity, BangumiSecondaryActivity.class);
						break;
					case 13:
						bundle = new Bundle();
//						bundle2.putInt(NewHistoryActivity.g, 1);
//						IntentHelper.a(activity, NewHistoryActivity.class, bundle2);
						break;
					case 14:
						try {
							action = Integer.valueOf(url).intValue();
						} catch (Exception e) {
							action = 0;
						}
//						IntentHelper.d(activity, i, "recommend");
						break;
					case 15:
						bundle = new Bundle();
						StringBuilder stringBuilder = new StringBuilder();
//						stringBuilder.append(b());
						stringBuilder.append(url);
						bundle.putString("url", stringBuilder.toString());
//						IntentHelper.a(activity, WebViewActivity.class, bundle2);
						break;
					case 16:
//						IntentHelper.c(activity);
						break;
					case 17:
						if (!TextUtils.isEmpty(url)) {
							if (bundle == null) {
								bundle = new Bundle();
							}
//							bundle.putString(GameDetailActivity.f, str);
//							IntentHelper.a(activity, GameDetailActivity.class, bundle);
							break;
						}
						break;
					case 19:
//						intent = new Intent(activity, ChannelHotActivity.class);
//						intent.putExtra("channel", str);
						break;
					default:
//						intent.setClass(activity, WebViewActivity.class);
//						StringBuilder stringBuilder2 = new StringBuilder();
//						stringBuilder2.append(DomainHelper.a().m());
//						stringBuilder2.append("/pleaseupdate/");
//						intent.putExtra("url", stringBuilder2.toString());
						break;
				}
				if (intent != null) {
					activity.startActivity(intent);
				}
			} catch (Throwable e) {
				CommonLog.loge( e );
			}
		}
	}
//    /**
//     * 获取选择照片的Intent
//     *
//     * @return
//     */
//    public static Intent getPickIntentWithGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        return intent.setType("image*//*");
//    }
//
//    /**
//     * 获取从文件中选择照片的Intent
//     *
//     * @return
//     */
//    public static Intent getPickIntentWithDocuments() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        return intent.setType("image*//*");
//    }
//
//
//    public static Intent buildImageGetIntent(final Uri saveTo, final int outputX, final int outputY, final boolean returnData) {
//        return buildImageGetIntent(saveTo, 1, 1, outputX, outputY, returnData);
//    }
//
//    public static Intent buildImageGetIntent(Uri saveTo, int aspectX, int aspectY,
//                                             int outputX, int outputY, boolean returnData) {
//        Intent intent = new Intent();
//        if (Build.VERSION.SDK_INT < 19) {
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//        } else {
//            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//        }
//        intent.setType("image*//*");
//        intent.putExtra("output", saveTo);
//        intent.putExtra("aspectX", aspectX);
//        intent.putExtra("aspectY", aspectY);
//        intent.putExtra("outputX", outputX);
//        intent.putExtra("outputY", outputY);
//        intent.putExtra("scale", true);
//        intent.putExtra("return-data", returnData);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
//        return intent;
//    }
//
//    public static Intent buildImageCropIntent(final Uri uriFrom, final Uri uriTo, final int outputX, final int outputY, final boolean returnData) {
//        return buildImageCropIntent(uriFrom, uriTo, 1, 1, outputX, outputY, returnData);
//    }
//
//    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int aspectX, int aspectY,
//                                              int outputX, int outputY, boolean returnData) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uriFrom, "image*//*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("output", uriTo);
//        intent.putExtra("aspectX", aspectX);
//        intent.putExtra("aspectY", aspectY);
//        intent.putExtra("outputX", outputX);
//        intent.putExtra("outputY", outputY);
//        intent.putExtra("scale", true);
//        intent.putExtra("return-data", returnData);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
//        return intent;
//    }
//
//    public static Intent buildImageCaptureIntent(final Uri uri) {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        return intent;
//    }
}
