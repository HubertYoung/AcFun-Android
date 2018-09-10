package com.hubertyoung.pay.share.image.resource;

import android.graphics.Bitmap;

import com.hubertyoung.pay.share.image.ImageTool;


public class UrlResource extends ImageResource {
    public final String url;

    public UrlResource(String url) {
        this.url = url;
    }

    @Override
    public String toUri() {
        return url;
    }

    @Override
    public Bitmap toBitmap() {
        return ImageTool.toBitmap(ImageTool.loadNetImage(url));
    }

    @Override
    public byte[] toBytes() {
        return ImageTool.loadNetImage(url);
    }
}