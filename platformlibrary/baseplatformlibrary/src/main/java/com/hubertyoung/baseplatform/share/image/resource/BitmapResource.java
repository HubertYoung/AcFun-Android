package com.hubertyoung.baseplatform.share.image.resource;

import android.graphics.Bitmap;

import com.hubertyoung.baseplatform.share.image.ImageTool;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/11 16:29
 * @since:V1.0.0
 * @desc:com.hubertyoung.baseplatform.share.image.resource
 */
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