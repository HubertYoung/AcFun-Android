package com.hubertyoung.baseplatform.share.shareparam;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.File;

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
public class ShareImage implements Parcelable {
    private static final int INVALID = -1;

    private File mLocalFile;
    private Bitmap mBitmap;
    private String mNetImageUrl;
    private int mResId = INVALID;

    public ShareImage(File localFile) {
        mLocalFile = localFile;
    }

    public ShareImage(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public ShareImage(String netImageUrl) {
        mNetImageUrl = netImageUrl;
    }

    public ShareImage(int resId) {
        mResId = resId;
    }

    public File getLocalFile() {
        return mLocalFile;
    }

    public String getLocalPath() {
        return mLocalFile == null ? null : mLocalFile.exists() ? mLocalFile.getAbsolutePath() : null;
    }

    public void setLocalFile(File localFile) {
        mLocalFile = localFile;
        mResId = INVALID;
        mNetImageUrl = null;
        mBitmap = null;
    }

    public String getNetImageUrl() {
        return mNetImageUrl;
    }

    public void setNetImageUrl(String imageUrl) {
        mNetImageUrl = imageUrl;
        mBitmap = null;
        mResId = INVALID;
        mLocalFile = null;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
        mLocalFile = null;
        mNetImageUrl = null;
        mBitmap = null;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        mResId = INVALID;
        mLocalFile = null;
        mNetImageUrl = null;
    }

    public boolean isNetImage() {
        return getImageType() == ImageType.NET;
    }

    public boolean isLocalImage() {
        return getImageType() == ImageType.LOCAL;
    }

    public boolean isBitmapImage() {
        return getImageType() == ImageType.BITMAP;
    }

    public boolean isResImage() {
        return getImageType() == ImageType.RES;
    }

    public boolean isUnknowImage() {
        return getImageType() == ImageType.UNKNOW;
    }

    public ImageType getImageType() {
        if (!TextUtils.isEmpty(mNetImageUrl)) {
            return ImageType.NET;
        } else if (mLocalFile != null && mLocalFile.exists()) {
            return ImageType.LOCAL;
        } else if (mResId != INVALID) {
            return ImageType.RES;
        } else if (mBitmap != null && !mBitmap.isRecycled()) {
            return ImageType.BITMAP;
        } else {
            return ImageType.UNKNOW;
        }
    }

    public enum ImageType {
        UNKNOW, LOCAL, NET, BITMAP, RES
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        dest.writeString(mLocalFile == null ? null : mLocalFile.getAbsolutePath());
        dest.writeParcelable(mBitmap, 0);
        dest.writeString(mNetImageUrl);
        dest.writeInt(mResId);
    }

    protected ShareImage(Parcel in) {
        String filePath = in.readString();
        mLocalFile = TextUtils.isEmpty(filePath) ? null : new File(filePath);
        mBitmap = in.readParcelable(Bitmap.class.getClassLoader());
        mNetImageUrl = in.readString();
        mResId = in.readInt();
    }

    public static final Parcelable.Creator<ShareImage> CREATOR = new Parcelable.Creator<ShareImage>() {
        public ShareImage createFromParcel(Parcel source) {
            return new ShareImage(source);
        }

        public ShareImage[] newArray(int size) {
            return new ShareImage[size];
        }
    };
}