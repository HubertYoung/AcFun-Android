package com.hubertyoung.baseplatform.share.image.resource;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.hubertyoung.baseplatform.share.image.ImageTool;


public class DrawableResource extends ImageResource {
    public final Drawable drawable;

    public DrawableResource(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toUri() {
        return null;
    }

    @Override
    public Bitmap toBitmap() {
        return ImageTool.toBitmap(drawable);
    }

    @Override
    public byte[] toBytes() {
        return ImageTool.toBytes(ImageTool.toBitmap(drawable), Bitmap.CompressFormat.JPEG);
    }
}