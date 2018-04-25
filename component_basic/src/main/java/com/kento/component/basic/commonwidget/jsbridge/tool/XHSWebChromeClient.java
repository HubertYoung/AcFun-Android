package com.kento.component.basic.commonwidget.jsbridge.tool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 作者：JIUU on 2017/3/14 09:48
 * QQ号：1344393464
 * 作用：自定义weclient
 */
public class XHSWebChromeClient extends WebChromeClient {


    private boolean REFRESHUI = true;

    private Activity activity;




    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;

    public static final int FILECHOOSER_RESULTCODE = 1;
    public static final int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 5;

    public static final int REQ_CAMERA = FILECHOOSER_RESULTCODE + 1;
    public static final int REQ_CHOOSE = REQ_CAMERA + 1;

    public XHSWebChromeClient(Activity activity){
        super();
        this.activity = activity;
    }

    //扩展浏览器上传文件
    //3.0++版本
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        REFRESHUI = false;
        openFileChooserImpl(uploadMsg);
    }

    //3.0--版本
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        REFRESHUI = false;
        openFileChooserImpl(uploadMsg);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        REFRESHUI = false;
        openFileChooserImpl(uploadMsg);
    }

    // For Android > 5.0
    public boolean onShowFileChooser (WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams) {
        REFRESHUI = false;
        openFileChooserImplForAndroid5(uploadMsg);
        return true;
    }
    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }
    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        activity.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    public boolean isREFRESHUI() {
        return REFRESHUI;
    }

    public void setREFRESHUI(boolean REFRESHUI) {
        this.REFRESHUI = REFRESHUI;
    }
    public ValueCallback<Uri> getmUploadMessage() {
        return mUploadMessage;
    }

    public ValueCallback<Uri[]> getmUploadMessageForAndroid5() {
        return mUploadMessageForAndroid5;
    }
    public void setmUploadMessage(ValueCallback<Uri> mUploadMessage) {
        this.mUploadMessage = mUploadMessage;
    }

    public void setmUploadMessageForAndroid5(ValueCallback<Uri[]> mUploadMessageForAndroid5) {
        this.mUploadMessageForAndroid5 = mUploadMessageForAndroid5;
    }
}