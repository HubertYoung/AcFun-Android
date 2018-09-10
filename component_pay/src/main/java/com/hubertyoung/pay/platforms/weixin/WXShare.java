package com.hubertyoung.pay.platforms.weixin;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.pay.sdk.OnCallback;
import com.hubertyoung.pay.sdk.Platform;
import com.hubertyoung.pay.sdk.ResultCode;
import com.hubertyoung.pay.share.IShareable;
import com.hubertyoung.pay.share.ShareData;
import com.hubertyoung.pay.share.ShareTo;
import com.hubertyoung.pay.share.media.IMediaObject;
import com.hubertyoung.pay.share.media.MoImage;
import com.hubertyoung.pay.share.media.MoMusic;
import com.hubertyoung.pay.share.media.MoVideo;
import com.hubertyoung.pay.share.media.MoWeb;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;


/**
 * Created by ezy on 17/3/18.
 */

public class WXShare extends WXBase implements IShareable {

    WXShare( Activity activity, Platform platform) {
        super(activity, platform);
    }

    @Override
    public void share( @NonNull final ShareData data, @NonNull final OnCallback<String> callback) {

        if (!mApi.isWXAppInstalled()) {
            callback.onFailed( mActivity, ResultCode.RESULT_FAILED, "ddpsdk_error_operate wechat" );
            return;
        }
        mCallback = callback;
        new AsyncTask<ShareData, Void, BaseReq>() {
            @Override
            protected BaseReq doInBackground(ShareData... params) {
                return makeReq(params[0]);
            }

            @Override
            protected void onPostExecute(BaseReq req) {
                mCallback.onStarted(mActivity);
                mApi.sendReq(req);
            }
        }.execute(data);
    }

    BaseReq makeReq(ShareData data) {

        WXMediaMessage message = new WXMediaMessage();
        if (data.hasTitle()) {
            message.title = data.title;
        }
        if (data.hasDescription()) {
            message.description = data.description;
        }
        if (data.hasThumb()) {
            message.thumbData = data.thumb.toBytes();
        }

        switch (data.type()) {
        case IMediaObject.TYPE_TEXT:
            message.mediaObject = new WXTextObject(data.text);
            break;
        case IMediaObject.TYPE_WEB:
            message.mediaObject = new WXWebpageObject(((MoWeb ) data.media).url);
            break;
        case IMediaObject.TYPE_EMOJI:
        case IMediaObject.TYPE_IMAGE: {
            MoImage mo = (MoImage) data.media;
            WXImageObject wxo = new WXImageObject();
            wxo.imagePath = mo.toUri();
            if ( TextUtils.isEmpty(wxo.imagePath)) {
                wxo.imageData = mo.toBytes();
            }
            message.mediaObject = wxo;
        }
        break;
        case IMediaObject.TYPE_VIDEO: {
            MoVideo mo = (MoVideo) data.media;
            WXVideoObject wxo = new WXVideoObject();
            wxo.videoUrl = mo.mediaUrl;
            wxo.videoLowBandUrl = mo.lowBandUrl;
            message.mediaObject = wxo;
        }
        break;
        case IMediaObject.TYPE_MUSIC: {
            MoMusic mo = (MoMusic) data.media;
            WXMusicObject wxo = new WXMusicObject();
            wxo.musicUrl = mo.mediaUrl;
            wxo.musicDataUrl = mo.mediaDataUrl;
            wxo.musicLowBandUrl = mo.lowBandUrl;
            wxo.musicLowBandDataUrl = mo.lowBandDataUrl;
            message.mediaObject = wxo;
        }
        break;

        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;
        req.scene = toScene(mPlatform.getName());

        return req;
    }

    @Override
    protected void onResultOk(SendMessageToWX.Resp resp) {
        mCallback.onSucceed(mActivity, "分享成功");
    }

    int toScene(String platform) {
        switch (platform) {
        case ShareTo.WXSession:
            return 0;
        case ShareTo.WXTimeline:
            return 1;
        case ShareTo.WXFavorite:
            return 2;
        }
        return 0;
    }


}
