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

package com.hubertyoung.baseplatform;

import android.content.Context;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.download.DefaultImageDownloader;
import com.hubertyoung.baseplatform.download.IImageDownloader;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 13:46
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform
 */
public class PlatformShareConfiguration implements Parcelable {

    private String mImageCachePath;
    private int mDefaultShareImage;

    private IImageDownloader mImageDownloader;
    private Executor mTaskExecutor;

    private PlatformShareConfiguration( Builder builder) {
        mImageCachePath = builder.mImageCachePath;
        mDefaultShareImage = builder.mDefaultShareImage;
        mImageDownloader = builder.mImageLoader;
        mTaskExecutor = Executors.newCachedThreadPool();
    }

    public String getImageCachePath( Context context) {
        if ( TextUtils.isEmpty(mImageCachePath)) {
            mImageCachePath = Builder.getDefaultImageCacheFile(context.getApplicationContext());
        }
        return mImageCachePath;
    }

    public int getDefaultShareImage() {
        return mDefaultShareImage;
    }

    public IImageDownloader getImageDownloader() {
        return mImageDownloader;
    }

    public Executor getTaskExecutor() {
        return mTaskExecutor;
    }

    public static class Builder {
        public static final String IMAGE_CACHE_FILE_NAME = "shareImage";

        private Context mContext;
        private String mImageCachePath;
        private int mDefaultShareImage = -1;

        private IImageDownloader mImageLoader;

        public Builder(Context context) {
            mContext = context.getApplicationContext();
        }

        public Builder imageCachePath(String path) {
            mImageCachePath = path;
            return this;
        }

        public Builder defaultShareImage(int defaultImage) {
            mDefaultShareImage = defaultImage;
            return this;
        }

        public Builder imageDownloader(IImageDownloader loader) {
            mImageLoader = loader;
            return this;
        }

        public PlatformShareConfiguration build() {
            checkFields();
            return new PlatformShareConfiguration(this);
        }

        private void checkFields() {
            File imageCacheFile = null;
            if (!TextUtils.isEmpty(mImageCachePath)) {
                imageCacheFile = new File(mImageCachePath);
                if (!imageCacheFile.isDirectory()) {
                    imageCacheFile = null;
                } else if (!imageCacheFile.exists() && !imageCacheFile.mkdirs()) {
                    imageCacheFile = null;
                }
            }
            if (imageCacheFile == null) {
                mImageCachePath = getDefaultImageCacheFile(mContext);
            }

            if (mImageLoader == null) {
                mImageLoader = new DefaultImageDownloader();
            }

            // TODO: 2018/9/14 默认为-1
//            if (mDefaultShareImage == -1) {
//                mDefaultShareImage = 0;
//            }
        }

        private static String getDefaultImageCacheFile( Context context) {
            String imageCachePath = null;
            File extCacheFile = context.getExternalCacheDir();
            if (extCacheFile == null) {
                extCacheFile = context.getExternalFilesDir( Environment.DIRECTORY_PICTURES);
            }
            if (extCacheFile != null) {
                imageCachePath = extCacheFile.getAbsolutePath() + File.separator + IMAGE_CACHE_FILE_NAME + File.separator;
                File imageCacheFile = new File(imageCachePath);
                imageCacheFile.mkdirs();
            }
            return imageCachePath;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        dest.writeString(this.mImageCachePath);
        dest.writeInt(this.mDefaultShareImage);
    }

    protected PlatformShareConfiguration( Parcel in) {
        this.mImageCachePath = in.readString();
        this.mDefaultShareImage = in.readInt();
        this.mImageDownloader = new DefaultImageDownloader();
        this.mTaskExecutor = Executors.newCachedThreadPool();
    }

    public static final Parcelable.Creator<PlatformShareConfiguration> CREATOR = new Parcelable.Creator<PlatformShareConfiguration>() {
        public PlatformShareConfiguration createFromParcel(Parcel source) {
            return new PlatformShareConfiguration(source);
        }

        public PlatformShareConfiguration[] newArray(int size) {
            return new PlatformShareConfiguration[size];
        }
    };
}
