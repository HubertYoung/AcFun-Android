package com.hubertyoung.baseplatform.share.image.resource;

import android.graphics.Bitmap;

import com.hubertyoung.baseplatform.share.image.ImageTool;

import java.io.File;



public class FileResource extends ImageResource {
    public final File file;

    public FileResource(File file) {
        this.file = file;
    }

    @Override
    public String toUri() {
        return file.toString();
    }

    @Override
    public Bitmap toBitmap() {
        return ImageTool.toBitmap(file);
    }

    @Override
    public byte[] toBytes() {
        return ImageTool.toBytes(ImageTool.toBitmap(file), Bitmap.CompressFormat.JPEG);
    }

}