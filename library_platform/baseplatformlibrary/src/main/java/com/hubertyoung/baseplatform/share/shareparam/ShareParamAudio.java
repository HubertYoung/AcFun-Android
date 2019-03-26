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
 * TODO 区分低带宽音乐/普通音乐
 */
public class ShareParamAudio extends BaseShareParam implements Parcelable {

    protected ShareAudio mAudio;

    public ShareParamAudio( String title, String content) {
        super(title, content);
    }

    public ShareParamAudio( String title, String content, String targetUrl) {
        super(title, content, targetUrl);
    }

    public ShareAudio getAudio() {
        return mAudio;
    }

    public void setAudio(ShareAudio audio) {
        mAudio = audio;
    }

    public ShareImage getThumb() {
        return mAudio == null ? null : mAudio.getThumb();
    }

    public String getAudioUrl() {
        return mAudio == null ? null : mAudio.getAudioSrcUrl();
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mAudio, 0);
    }

    protected ShareParamAudio(Parcel in) {
        super(in);
        mAudio = in.readParcelable(ShareAudio.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShareParamAudio> CREATOR = new Parcelable.Creator<ShareParamAudio>() {
        public ShareParamAudio createFromParcel(Parcel source) {
            return new ShareParamAudio(source);
        }

        public ShareParamAudio[] newArray(int size) {
            return new ShareParamAudio[size];
        }
    };
}
