package com.hubertyoung.common.utils.os;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.hubertyoung.common.CommonApplication;


/**
 * 作者：JIUU on 2017/6/18 21:20
 * QQ号：1344393464
 * 作用：剪贴板
 */
public class ClipboardUtils {
	private ClipboardUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 复制文本到剪贴板
	 *
	 * @param text 文本
	 */
	public static void copyText(CharSequence text) {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
	}

	/**
	 * 获取剪贴板的文本
	 *
	 * @return 剪贴板的文本
	 */
	public static CharSequence getText() {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = clipboard.getPrimaryClip();
		if (clip != null && clip.getItemCount() > 0) {
			return clip.getItemAt(0).coerceToText( CommonApplication.getAppContext());
		}
		return "";
	}

	/**
	 * 复制uri到剪贴板
	 *
	 * @param uri uri
	 */
	public static void copyUri(Uri uri) {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(ClipData.newUri( CommonApplication.getAppContext().getContentResolver(), "uri", uri));
	}

	/**
	 * 获取剪贴板的uri
	 *
	 * @return 剪贴板的uri
	 */
	public static Uri getUri() {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = clipboard.getPrimaryClip();
		if (clip != null && clip.getItemCount() > 0) {
			return clip.getItemAt(0).getUri();
		}
		return Uri.parse("");
	}

	/**
	 * 复制意图到剪贴板
	 *
	 * @param intent 意图
	 */
	public static void copyIntent(Intent intent) {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(ClipData.newIntent("intent", intent));
	}

	/**
	 * 获取剪贴板的意图
	 * 假说有一个应用通过某种类型的用户交互在剪切板中创建，增加一个包含了被认为是“公用”组件的Intent对象的ClipData
	 * 恶意应用可能会设置一个监听器来接收新增ClipData的通知
	 * 一旦检测到新的ClipData，它可以通过在剪切板增加一个包含了新的Intent对象的恶意ClipData
	 *TODO 待优化
	 *
	 * @return 剪贴板的意图
	 */
	public static Intent getIntent() {
		ClipboardManager clipboard = (ClipboardManager) CommonApplication.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = clipboard.getPrimaryClip();
		if (clip != null && clip.getItemCount() > 0) {
			return clip.getItemAt(0).getIntent();
		}
		return null;
	}
}
