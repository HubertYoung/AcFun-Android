package com.hubertyoung.baseplatform.share.shareparam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

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
public abstract class BaseShareParam implements Parcelable {

    protected String mTitle;
    protected String mContent;
    protected String mTargetUrl;

    private Map<String, Object> mExtraMap = new HashMap<>();

    public BaseShareParam() {
    }

    public BaseShareParam( String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public BaseShareParam( String title, String content, String targetUrl) {
        mTitle = title;
        mContent = content;
        mTargetUrl = targetUrl;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTargetUrl() {
        return mTargetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        mTargetUrl = targetUrl;
    }

    public void putExtra( String key, Object value) {
        mExtraMap.put(key, value);
    }

    public Object getExtra( String key) {
        return mExtraMap.get(key);
    }

    public void setExtraMap(Map<String, Object> extraMap) {
        mExtraMap = extraMap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeString(mTargetUrl);
        try {
            dest.writeMap(mExtraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected BaseShareParam(Parcel in) {
        mTitle = in.readString();
        mContent = in.readString();
        mTargetUrl = in.readString();
        try {
            in.readMap(mExtraMap, Map.class.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
