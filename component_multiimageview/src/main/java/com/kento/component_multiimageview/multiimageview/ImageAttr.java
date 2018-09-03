package com.hubertyoung.component_multiimageview.multiimageview;

import java.io.Serializable;

public class ImageAttr implements Serializable {

    public String Pic;
    public String thumbnailUrl;

    // 显示的宽高
    public int width;
    public int height;

    // 真实的高度
    public int realWidth;
    public int realHeight;

    // 左上角坐标
    public int left;
    public int top;

    public ImageAttr( String pic ) {
        Pic = pic;
    }

    @Override
    public String toString() {
        return "ImageAttr{" +
                "width=" + width +
                ", height=" + height +
                ", realWidth=" + realWidth +
                ", realHeight=" + realHeight +
                ", left=" + left +
                ", top=" + top +
                '}';
    }
}
