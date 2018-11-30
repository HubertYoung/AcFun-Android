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

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hubertyoung.baseplatform.error.ShareException;

import java.io.File;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 11:03
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.download
 */
public abstract class AbsImageDownloader implements IImageDownloader {
    private static final String TAG = AbsImageDownloader.class.getSimpleName();

    @Override
    public final void download( Context context, String imageUrl, String targetFileDirPath, OnImageDownloadListener listener) throws ShareException {
        if ( TextUtils.isEmpty(imageUrl)) {
            Log.d(TAG, "null image url");
            if (listener != null) {
                listener.onFailed(imageUrl);
            }
        } else {
            String filePath = createFileIfNeed(context, imageUrl, targetFileDirPath);
            if ( TextUtils.isEmpty(filePath)) {
                Log.e(TAG, "create image file failed");
                if (listener != null) {
                    listener.onFailed(imageUrl);
                }
                return;
            }

            File targetFile = new File(filePath);
            if (targetFile.exists()) {
                Log.d(TAG, "image already downloaded");
                if (listener != null) {
                    listener.onSuccess(targetFile.getAbsolutePath());
                }
                return;
            }

            downloadDirectly(imageUrl, filePath, listener);
        }
    }

    protected abstract void downloadDirectly( String imageUrl, String filePath, OnImageDownloadListener listener);

    protected String createFileIfNeed( Context context, String imageUrl, String targetFileDirPath) throws ShareException {
        if (context == null || TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(targetFileDirPath)) {
            return null;
        }

        File targetFileDir = new File(targetFileDirPath);
        String fileName = String.valueOf(imageUrl.hashCode());
        File targetFile = new File(targetFileDir, fileName);
        if (targetFile.exists()) {
            return targetFile.getAbsolutePath();
        }

        if (!targetFileDir.exists() && !targetFileDir.mkdirs()) {
            return null;
        }

        return targetFile.getAbsolutePath();
    }

}
