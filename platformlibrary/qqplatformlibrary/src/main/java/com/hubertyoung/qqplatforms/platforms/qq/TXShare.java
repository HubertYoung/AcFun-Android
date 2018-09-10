package com.hubertyoung.qqplatforms.platforms.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.IShareable;
import com.hubertyoung.baseplatform.share.ShareData;
import com.hubertyoung.baseplatform.share.ShareTo;
import com.hubertyoung.baseplatform.share.media.IMediaObject;
import com.hubertyoung.baseplatform.share.media.MoImage;
import com.hubertyoung.baseplatform.share.media.MoMusic;
import com.hubertyoung.baseplatform.share.media.MoVideo;
import com.hubertyoung.baseplatform.share.media.MoWeb;
import com.hubertyoung.baseplatform.tools.PayLogUtil;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 18:06
 * @since:V1.0.0
 * @desc:com.hubertyoung.qqplatforms.platforms.qq
 */
public class TXShare implements IShareable {
    public static final String TAG = TXShare.class.getSimpleName();

    private static final String KEY_REQ_TYPE = QQShare.SHARE_TO_QQ_KEY_TYPE;
    private static final String KEY_TARGET_URL = QQShare.SHARE_TO_QQ_TARGET_URL;
    private static final String KEY_IMAGE_URL = QQShare.SHARE_TO_QQ_IMAGE_URL;
    private static final String KEY_IMAGE_LOCAL_URL = QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL;
    private static final String KEY_AUDIO_URL = QQShare.SHARE_TO_QQ_AUDIO_URL;
    private static final String KEY_AUDIO_VIDEO_PATH = QzonePublish.PUBLISH_TO_QZONE_VIDEO_PATH;

    private static final int REQ_TYPE_DEFAULT = 1;
    private static final int REQ_TYPE_AUDIO = 2;
    private static final int REQ_TYPE_MOOD = 3;
    private static final int REQ_TYPE_VIDEO = 4;
    private static final int REQ_TYPE_IMAGE = 5;

    Activity mActivity;
    OtherPlatform mPlatform;
    Tencent mApi;

    IUiListener mListener;

    TXShare( Activity activity, OtherPlatform platform) {
        mActivity = activity;
        mPlatform = platform;
        mApi = Tencent.createInstance(platform.getAppId(), mActivity);
    }

    String toMessage(UiError error) {
        return "[" + error.errorCode + "]" + error.errorMessage;
    }

    @Override
    public void share( @NonNull final ShareData data, @NonNull final OnCallback<String> callback) {

        final boolean isShareToQQ = mPlatform.getName().equals( ShareTo.QQ);
        mListener = new IUiListener() {
            @Override
            public void onComplete(Object response) {
                PayLogUtil.loge(TAG, "response ==> " + response);
                if (response instanceof JSONObject && ((JSONObject ) response).length() > 0) {
                    callback.onSuccess(mActivity, "");
                } else {
                    callback.onError(mActivity, ResultCode.RESULT_FAILED, "分享失败: 返回为空");
                }
                callback.onCompleted(mActivity);
            }

            @Override
            public void onError(UiError e) {
                PayLogUtil.loge(TAG, "错误信息: " + e.errorDetail);
                callback.onError(mActivity, ResultCode.RESULT_FAILED, toMessage(e));
            }

            @Override
            public void onCancel() {
                callback.onError(mActivity, ResultCode.RESULT_CANCELLED, "用户取消了分享");
            }
        };
        Bundle bundle = new Bundle();

        if (data.hasText()) {
            bundle.putString( QQShare.SHARE_TO_QQ_TITLE, data.text);
        } else if (data.hasTitle()) {
            bundle.putString( QQShare.SHARE_TO_QQ_TITLE, data.title);
        }
        if (data.hasDescription()) {
            bundle.putString( QQShare.SHARE_TO_QQ_SUMMARY, data.description);
        }
        if (data.hasUrl()) {
            bundle.putString( QQShare.SHARE_TO_QQ_TARGET_URL, data.url);
        }
        bundle.putString( QQShare.SHARE_TO_QQ_APP_NAME, mActivity.getPackageManager().getApplicationLabel(mActivity.getApplicationInfo()).toString());
        putImageUrl(data, bundle, isShareToQQ);

        boolean unsupported = false;
        switch (data.type()) {
        case IMediaObject.TYPE_WEB:
            bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_DEFAULT);
            bundle.putString( QQShare.SHARE_TO_QQ_TARGET_URL, ((MoWeb ) data.media).url);
            shareTo(bundle, isShareToQQ, false, callback);
            break;
        case IMediaObject.TYPE_EMOJI:
        case IMediaObject.TYPE_IMAGE:
            if (isShareToQQ) {
                bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_IMAGE);
                bundle.putString(KEY_IMAGE_LOCAL_URL, ((MoImage ) data.media).toUri());
            } else {
                bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_MOOD);
            }
            shareTo(bundle, isShareToQQ, true, callback);
            break;
        case IMediaObject.TYPE_MUSIC:
            unsupported = !isShareToQQ;
            bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_AUDIO);
            bundle.putString(KEY_AUDIO_URL, ((MoMusic ) data.media).mediaUrl);
            shareTo(bundle, true, false, callback);
            break;
        case IMediaObject.TYPE_TEXT:
            bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_MOOD);
            shareTo(bundle, isShareToQQ, true, callback);
            unsupported = isShareToQQ;
            break;
        case IMediaObject.TYPE_VIDEO:
            bundle.putInt(KEY_REQ_TYPE, REQ_TYPE_AUDIO);
            bundle.putString(KEY_AUDIO_VIDEO_PATH, ((MoVideo ) data.media).mediaUrl);
            shareTo(bundle, false, true, callback);
            unsupported = isShareToQQ;
            break;
        default:
            unsupported = true;
            // unsupported
            break;
        }
        if (unsupported) {
            callback.onError(mActivity, ResultCode.RESULT_FAILED, "不支持的分享类型");
        }
    }

    void putImageUrl( ShareData data, Bundle bundle, boolean isShareToQQ) {

        if (data.media instanceof MoImage) {
            if (isShareToQQ) {
                bundle.putString(KEY_IMAGE_URL, ((MoImage) data.media).toUri());
            } else {
                bundle.putStringArrayList(KEY_IMAGE_URL, imageList(((MoImage) data.media).toUri()));
            }
        } else if (data.hasThumb()) {
            if (isShareToQQ) {
                bundle.putString(KEY_IMAGE_URL, data.thumb.toUri());
            } else {
                bundle.putStringArrayList(KEY_IMAGE_URL, imageList(data.thumb.toUri()));
            }
        }
    }

    void shareTo( Bundle bundle, boolean isShareToQQ, boolean isPublishToQZone, OnCallback<String> callback) {

        if (isShareToQQ) {
            callback.onStart(mActivity);
            mApi.shareToQQ(mActivity, bundle, mListener);
        } else if (isPublishToQZone) {
            callback.onStart(mActivity);
            mApi.publishToQzone(mActivity, bundle, mListener);
        } else {
            callback.onStart(mActivity);
            mApi.shareToQzone(mActivity, bundle, mListener);
        }
        PayLogUtil.loge(TAG, bundle.toString());
    }

    ArrayList<String> imageList(String image) {
        ArrayList<String> images = new ArrayList();
        images.add(image);
        return images;
    }


    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
        }
    }
}
