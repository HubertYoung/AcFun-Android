package com.hubertyoung.baseplatform.share.media;

import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.share.image.resource.ImageResource;

public class MoWeb extends IMediaObject {

    public String url;

    public ImageResource getImageResource() {
        return mImageResource;
    }

    public MoWeb setImageResource( ImageResource imageResource ) {
        mImageResource = imageResource;
        return this;
    }

    public ImageResource mImageResource;

    public MoWeb(@NonNull String url) {
        this.url = url;
    }

    @Override
    public int type() {
        return TYPE_WEB;
    }
}