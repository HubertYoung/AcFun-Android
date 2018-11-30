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
public class ShareParamWebPage extends BaseShareParam {
    protected ShareImage mThumb;

    public ShareParamWebPage() {
    }

    public ShareParamWebPage( String title, String content) {
        super(title, content);
    }

    public ShareParamWebPage( String title, String content, String targetUrl) {
        super(title, content, targetUrl);
    }

    public ShareImage getThumb() {
        return mThumb;
    }

    public void setThumb(ShareImage thumb) {
        mThumb = thumb;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mThumb, 0);
    }

    protected ShareParamWebPage(Parcel in) {
        super(in);
        mThumb = in.readParcelable(ShareImage.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShareParamWebPage> CREATOR = new Parcelable.Creator<ShareParamWebPage>() {
        public ShareParamWebPage createFromParcel(Parcel source) {
            return new ShareParamWebPage(source);
        }

        public ShareParamWebPage[] newArray(int size) {
            return new ShareParamWebPage[size];
        }
    };
}
