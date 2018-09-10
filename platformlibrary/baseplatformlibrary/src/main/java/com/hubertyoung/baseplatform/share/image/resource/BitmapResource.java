package com.hubertyoung.baseplatform.share.image.resource;

import android.graphics.Bitmap;

import com.hubertyoung.baseplatform.share.image.ImageTool;


public class BitmapResource extends ImageResource {
    public final Bitmap bitmap;

    public BitmapResource(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toUri() {
        return null;
    }

    @Override
    public Bitmap toBitmap() {
        return bitmap;
    }

    @Override
    public byte[] toBytes() {
        return ImageTool.toBytes(bitmap, Bitmap.CompressFormat.JPEG);
    }
}