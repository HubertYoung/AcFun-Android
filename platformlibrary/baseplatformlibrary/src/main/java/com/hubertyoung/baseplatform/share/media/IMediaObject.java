package com.hubertyoung.baseplatform.share.media;

public abstract class IMediaObject {
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;
    public static final int TYPE_TEXT_IMAGE = 3;
    public static final int TYPE_EMOJI = 4;
    public static final int TYPE_MUSIC = 5;
    public static final int TYPE_VIDEO = 6;
    public static final int TYPE_WEB = 7;
    public static final int TYPE_FILE = 8;
    public abstract int type();
}