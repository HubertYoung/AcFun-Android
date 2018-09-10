package com.hubertyoung.baseplatform.share.image.resource;

import android.graphics.Bitmap;

public abstract class ImageResource {

    public abstract String toUri();

    public abstract Bitmap toBitmap();

    public abstract byte[] toBytes();
}