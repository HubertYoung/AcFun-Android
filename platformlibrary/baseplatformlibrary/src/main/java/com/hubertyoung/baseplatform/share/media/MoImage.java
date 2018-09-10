package com.hubertyoung.baseplatform.share.media;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.hubertyoung.baseplatform.share.image.resource.BitmapResource;
import com.hubertyoung.baseplatform.share.image.resource.BytesResource;
import com.hubertyoung.baseplatform.share.image.resource.FileResource;
import com.hubertyoung.baseplatform.share.image.resource.ImageResource;
import com.hubertyoung.baseplatform.share.image.resource.UrlResource;

import java.io.File;



public class MoImage extends IMediaObject {

    public ImageResource resource;

    public MoImage(@NonNull ImageResource resource) {
        this.resource = resource;
    }

    public MoImage(String url) {
        resource = new UrlResource(url);
    }

    public MoImage(File file) {
        resource = new FileResource(file);
    }

    public MoImage(byte[] bytes) {
        resource = new BytesResource(bytes);
    }

    public MoImage(Bitmap bitmap) {
        resource = new BitmapResource(bitmap);
    }


    @Override
    public int type() {
        return TYPE_IMAGE;
    }

    public String toUri() {
        return resource.toUri();
    }

    public byte[] toBytes() {
        return resource.toBytes();
    }
}