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
public class ShareAudio implements Parcelable {

    private ShareImage mThumb;
    private String mAudioSrcUrl;
    private String mAudioH5Url;
    private String mDescription;

    public ShareAudio() {
    }

    public ShareAudio( String audioSrcUrl, String audioH5Url, String description) {
        mAudioSrcUrl = audioSrcUrl;
        mAudioH5Url = audioH5Url;
        mDescription = description;
    }

    public ShareAudio( ShareImage thumb, String audioSrcUrl, String description) {
        mThumb = thumb;
        mAudioSrcUrl = audioSrcUrl;
        mDescription = description;
    }

    public ShareImage getThumb() {
        return mThumb;
    }

    public void setThumb(ShareImage thumb) {
        mThumb = thumb;
    }

    public String getAudioSrcUrl() {
        return mAudioSrcUrl;
    }

    public void setAudioSrcUrl(String audioSrcUrl) {
        mAudioSrcUrl = audioSrcUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAudioH5Url() {
        return mAudioH5Url;
    }

    public void setAudioH5Url(String audioH5Url) {
        mAudioH5Url = audioH5Url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        dest.writeParcelable(mThumb, 0);
        dest.writeString(mAudioSrcUrl);
        dest.writeString(mAudioH5Url);
        dest.writeString(mDescription);
    }

    protected ShareAudio(Parcel in) {
        mThumb = in.readParcelable(ShareImage.class.getClassLoader());
        mAudioSrcUrl = in.readString();
        mAudioH5Url = in.readString();
        mDescription = in.readString();
    }

    public static final Parcelable.Creator<ShareAudio> CREATOR = new Parcelable.Creator<ShareAudio>() {
        public ShareAudio createFromParcel(Parcel source) {
            return new ShareAudio(source);
        }

        public ShareAudio[] newArray(int size) {
            return new ShareAudio[size];
        }
    };
}
