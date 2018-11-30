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

import com.hubertyoung.baseplatform.error.ShareException;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 11:01
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.download
 */
public interface IImageDownloader {

    void download( Context context, String imageUrl, String targetFileDirPath, OnImageDownloadListener listener ) throws ShareException;

    interface OnImageDownloadListener {

        void onStart();

        void onSuccess( String filePath );

        void onFailed( String url );

    }
}
