package com.hubertyoung.baseplatform.share.shareparam;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * Time::2018/9/14 09:40
 * @since:V1.0.0
 * Pkg:com.hubertyoung.baseplatform.share.shareparam
 */
public class ShareParamVideo extends BaseShareParam {

    protected ShareVideo mVideo;

    public ShareParamVideo() {
    }

    public ShareParamVideo( String title, String content) {
        super(title, content);
    }

    public ShareParamVideo( String title, String content, String targetUrl) {
        super(title, content, targetUrl);
    }

    public ShareVideo getVideo() {
        return mVideo;
    }

    public void setVideo(ShareVideo video) {
        mVideo = video;
    }

    public ShareImage getThumb() {
        return mVideo == null ? null : mVideo.getThumb();
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mVideo, flags);
    }

    protected ShareParamVideo(Parcel in) {
        super(in);
        mVideo = in.readParcelable(ShareVideo.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShareParamVideo> CREATOR = new Parcelable.Creator<ShareParamVideo>() {
        public ShareParamVideo createFromParcel(Parcel source) {
            return new ShareParamVideo(source);
        }

        public ShareParamVideo[] newArray(int size) {
            return new ShareParamVideo[size];
        }
    };
}
