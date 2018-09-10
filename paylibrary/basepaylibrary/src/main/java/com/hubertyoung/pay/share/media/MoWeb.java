package com.hubertyoung.pay.share.media;

import android.support.annotation.NonNull;

public class MoWeb extends IMediaObject {

    public String url;

    public MoWeb(@NonNull String url) {
        this.url = url;
    }

    @Override
    public int type() {
        return TYPE_WEB;
    }
}