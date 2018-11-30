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

package com.hubertyoung.qqplatforms.platforms.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.sdk.ResultCode;
import com.hubertyoung.baseplatform.share.shareparam.BaseShareParam;
import com.hubertyoung.baseplatform.share.shareparam.ShareImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamAudio;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamText;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamVideo;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;
import com.hubertyoung.baseplatform.tools.PlatformLogUtil;
import com.hubertyoung.baseplatformlibrary.R;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

/**
 * <br>
 * function: * 只支持图文模式
 * 官方提示:QZone接口暂不支持发送多张图片的能力，若传入多张图片，则会自动选入第一张图片作为预览图。多图的能力将会在以后支持。
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 14:57
 * @since:V1.0.0
 * @desc:com.hubertyoung.qqplatforms.platforms.qq
 */
public class QQZoneShareHandler extends BaseQQShareHandler {

    QQZoneShareHandler( @NonNull Activity activity, @NonNull OtherPlatform platform ) {
        super( activity, platform );
    }
    
    public void onResult( int requestCode, int resultCode, Intent data ) {
        if (requestCode == Constants.REQUEST_QZONE_SHARE ) {
            Tencent.onActivityResultData( requestCode, resultCode, data, mUiListener );
        }
    }

    @Override
    public void shareText( ShareParamText params, OnCallback< String > callback ) {
       PlatformLogUtil.logd("share text");
        shareImageText(params, null,callback);
    }

    @Override
    public void shareImage( ShareParamImage params, OnCallback< String > callback ) {
       PlatformLogUtil.logd("share image");
        shareImageText(params, params.getImage(),callback);
    }

    @Override
    public void shareWebPage( ShareParamWebPage params, OnCallback< String > callback ) {
       PlatformLogUtil.logd("share web page");
        shareImageText(params, params.getThumb(),callback);
    }

    @Override
    public void shareAudio( ShareParamAudio params, OnCallback< String > callback ) {
       PlatformLogUtil.logd("share audio");
        shareImageText(params, params.getThumb(),callback);
    }

    @Override
    public void shareVideo( ShareParamVideo params, OnCallback< String > callback ) {
       PlatformLogUtil.logd("share video");
        shareImageText(params, params.getThumb(),callback);
    }

    private void shareImageText( BaseShareParam params, ShareImage image, OnCallback< String > callback ) {
        if ( TextUtils.isEmpty(params.getTitle()) || TextUtils.isEmpty(params.getTargetUrl())) {
            PlatformLogUtil.logd( "Title or target url is empty or illegal" );
            callback.onError( mActivity, ResultCode.RESULT_FAILED, mActivity.getString( R.string.sdk_platform_unsupported_sharing_types ) );
        }

       PlatformLogUtil.logd("share image text");
        Bundle bundle = new Bundle();
        bundle.putInt( QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString( QzoneShare.SHARE_TO_QQ_TITLE, params.getTitle());
        bundle.putString( QzoneShare.SHARE_TO_QQ_SUMMARY, params.getContent());
        bundle.putString( QzoneShare.SHARE_TO_QQ_TARGET_URL, params.getTargetUrl());

        ArrayList<String> imageUrls = new ArrayList<>();
        if (image != null) {
            if (image.isNetImage()) {
                imageUrls.add(image.getNetImageUrl());
            } else if (image.isLocalImage()) {
                imageUrls.add(image.getLocalPath());
            }
        }
        bundle.putStringArrayList( QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);

        doShareToQQ(mActivity, bundle);
    }

    @Override
    public void onShare( Activity activity, Tencent tencent, Bundle params, IUiListener iUiListener) {
        tencent.shareToQzone(activity, params, iUiListener);
    }
}
