package com.hubertyoung.baseplatform.download;

import android.content.Context;

import com.hubertyoung.baseplatform.error.ShareException;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * Time::2018/9/14 11:01
 * @since:V1.0.0
 * Pkg:com.hubertyoung.baseplatform.download
 */
public interface IImageDownloader {

    void download( Context context, String imageUrl, String targetFileDirPath, OnImageDownloadListener listener ) throws ShareException;

    interface OnImageDownloadListener {

        void onStart();

        void onSuccess( String filePath );

        void onFailed( String url );

    }
}
