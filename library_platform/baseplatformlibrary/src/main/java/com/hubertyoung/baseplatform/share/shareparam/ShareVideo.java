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

package com.hubertyoung.baseplatform.share.shareparam;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 09:40
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.share.shareparam
 */
public class ShareVideo implements Parcelable {

    private ShareImage mThumb;
    //源地址，比如http://baidu.com/..../hellowworld.mp4
    private String mVideoSrcUrl;
    private String mVideoH5Url;
    private String mDescription;

    public ShareVideo(){

    }

    public ShareVideo( ShareImage thumb, String videoH5Url, String description) {
        mThumb = thumb;
        mVideoH5Url = videoH5Url;
        mDescription = description;
    }

    public ShareVideo(ShareImage thumb, String videoH5Url) {
        mThumb = thumb;
        mVideoH5Url = videoH5Url;
    }

    public ShareImage getThumb() {
        return mThumb;
    }

    public void setThumb(ShareImage thumb) {
        mThumb = thumb;
    }

    public String getVideoSrcUrl() {
        return mVideoSrcUrl;
    }

    public void setVideoSrcUrl(String videoSrcUrl) {
        mVideoSrcUrl = videoSrcUrl;
    }

    public String getVideoH5Url() {
        return mVideoH5Url;
    }

    public void setVideoH5Url(String videoH5Url) {
        mVideoH5Url = videoH5Url;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        dest.writeParcelable(mThumb, 0);
        dest.writeString(mVideoSrcUrl);
        dest.writeString(mVideoH5Url);
        dest.writeString(mDescription);
    }

    protected ShareVideo(Parcel in) {
        mThumb = in.readParcelable(ShareImage.class.getClassLoader());
        mVideoSrcUrl = in.readString();
        mVideoH5Url = in.readString();
        mDescription = in.readString();
    }

    public static final Parcelable.Creator<ShareVideo> CREATOR = new Parcelable.Creator<ShareVideo>() {
        public ShareVideo createFromParcel(Parcel source) {
            return new ShareVideo(source);
        }

        public ShareVideo[] newArray(int size) {
            return new ShareVideo[size];
        }
    };
}
