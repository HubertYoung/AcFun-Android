/*
 * Copyright (C) 2015 Bilibili <jungly.ik@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hubertyoung.baseplatform.download;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.hubertyoung.baseplatform.tools.FileUtil;
import com.hubertyoung.baseplatform.tools.IOUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 10:59
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.download
 */
public class DefaultImageDownLoadTask extends Thread {
    private static final String TAG = DefaultImageDownLoadTask.class.getSimpleName();
    private static final String TEMP_EXTENSION = ".temp";

    private Handler mHandler = new Handler( Looper.getMainLooper());

    private String mDownloadUrl;
    private String mFilePath;

    private IImageDownloader.OnImageDownloadListener mDownLoadListener;

    public DefaultImageDownLoadTask( String downloadUrl, String filePath, IImageDownloader.OnImageDownloadListener downLoadListener) {
        mDownloadUrl = downloadUrl;
        mFilePath = filePath;
        mDownLoadListener = downLoadListener;
    }

    @Override
    public void run() {
        super.run();
        File tmpFile = new File(mFilePath + TEMP_EXTENSION);
        File parentFile = tmpFile.getParentFile();
        if (!parentFile.isDirectory() || (!parentFile.exists() && !parentFile.mkdirs())) {
            Log.e(TAG, "image download: environment init failed");
            onPostExecute(null);
            return;
        }
        HttpURLConnection conn;
        try {
            URL url = new URL(mDownloadUrl);
            conn = (HttpURLConnection ) url.openConnection();
        } catch (Exception e) {
            Log.w(TAG, "image download: http connect failed");
            onPostExecute(null);
            return;
        }
        try {
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Referer", mDownloadUrl);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
            conn.setInstanceFollowRedirects(true);
            conn.connect();
            int code = conn.getResponseCode();
            OutputStream out = null;
            InputStream in = null;

            try {
                if (code == HttpURLConnection.HTTP_OK) {
                    out = new BufferedOutputStream(new FileOutputStream(tmpFile));
                    in = conn.getInputStream();
                    IOUtil.copyLarge(in, out);
                } else {
                    Log.w(TAG, String.format("image download failed: http code (%d)", code));
                    mFilePath = null;
                }
            } catch (IOException e) {
                Log.w(TAG, "image download failed", e);
                mFilePath = null;
            } finally {
                IOUtil.closeQuietly(out);
                IOUtil.closeQuietly(in);
            }
            if (mFilePath != null) {
                File file = new File(mFilePath);
                if (!tmpFile.renameTo(file)) {
                    FileUtil.copyFile(tmpFile, file);
                    tmpFile.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.w(TAG, "image download failed", e);
            mFilePath = null;
        } finally {
            conn.disconnect();
        }

        onPostExecute(mFilePath);
    }

    protected void onPostExecute(final String filePath) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if ( TextUtils.isEmpty(mFilePath)) {
                    onDownloadFailed();
                } else {
                    onDownloadSuccess();
                }
            }
        });
    }

    private void onDownloadFailed() {
        Log.d(TAG, "image download failed");
        if (mDownLoadListener != null) {
            mDownLoadListener.onFailed(mDownloadUrl);
        }
    }

    private void onDownloadSuccess() {
        Log.d(TAG, "image download success");
        if (mDownLoadListener != null) {
            mDownLoadListener.onSuccess(mFilePath);
        }
    }

}
