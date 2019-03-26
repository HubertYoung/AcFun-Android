package com.hubertyoung.baseplatform.download;

public class DefaultImageDownloader extends AbsImageDownloader {

    @Override
    protected void downloadDirectly( String imageUrl, String filePath, OnImageDownloadListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        new DefaultImageDownLoadTask(imageUrl, filePath, listener).start();
    }

}
