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

package com.hubertyoung.wechatplatforms.platforms.weixin;

import android.app.Activity;

import com.hubertyoung.baseplatform.sdk.OnCallback;
import com.hubertyoung.baseplatform.sdk.OtherPlatform;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamImage;
import com.hubertyoung.baseplatform.share.shareparam.ShareParamWebPage;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 15:34
 * @since:V1.0.0
 * @desc:com.hubertyoung.wechatplatforms.platforms.weixin
 */
public class WXMomentShareHandler extends BaseWxShareHandler {
	WXMomentShareHandler( Activity activity, OtherPlatform platform ) {
		super( activity, platform );
	}

    @Override
    public void shareImage( ShareParamImage params, OnCallback mCallback ) {
        if (params.getImage() != null && (!params.getImage().isUnknowImage())) {
            super.shareImage(params,mCallback);
        } else {
            ShareParamWebPage webpage = new ShareParamWebPage(params.getTitle(), params.getContent(), params.getTargetUrl());
            webpage.setThumb(params.getImage());
            shareWebPage(webpage,mCallback);
        }
    }
}

