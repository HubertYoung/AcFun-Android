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

/**
 * @author Jungly
 * @email jungly.ik@gmail.com
 * @since 2015/10/24 22:59
 */
public class DefaultImageDownloader extends AbsImageDownloader {

    @Override
    protected void downloadDirectly( String imageUrl, String filePath, OnImageDownloadListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        new DefaultImageDownLoadTask(imageUrl, filePath, listener).start();
    }

}
