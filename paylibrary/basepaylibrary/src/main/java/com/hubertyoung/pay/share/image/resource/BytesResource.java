package com.hubertyoung.pay.share.image.resource;

import android.graphics.Bitmap;

import com.hubertyoung.pay.share.image.ImageTool;


public class BytesResource extends ImageResource {
    public final byte[] bytes;

    public BytesResource(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toUri() {
        return null;
    }

    @Override
    public Bitmap toBitmap() {
        return ImageTool.toBitmap(bytes);
    }

    @Override
    public byte[] toBytes() {
        return bytes;
    }
}