package com.hubertyoung.pay.share.image.resource;

import android.graphics.Bitmap;

public abstract class ImageResource {

    public abstract String toUri();

    public abstract Bitmap toBitmap();

    public abstract byte[] toBytes();
}