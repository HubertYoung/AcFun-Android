package com.hubertyoung.baseplatform.share.media;

import android.graphics.Bitmap;

import com.hubertyoung.baseplatform.share.image.resource.ImageResource;

import java.io.File;



public class MoEmoji extends MoImage {

    public MoEmoji(ImageResource source) {
        super(source);
    }

    public MoEmoji(File file) {
        super(file);
    }

    public MoEmoji(byte[] bytes) {
        super(bytes);
    }

    public MoEmoji(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public int type() {
        return TYPE_EMOJI;
    }
}