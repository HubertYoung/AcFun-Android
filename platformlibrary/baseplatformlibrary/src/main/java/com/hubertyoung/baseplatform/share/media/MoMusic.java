package com.hubertyoung.baseplatform.share.media;

public class MoMusic extends IMediaObject {

    public String mediaUrl;
    public String mediaDataUrl;
    public String lowBandUrl;
    public String lowBandDataUrl;
    public int duration;

    public MoMusic() {
    }

    public MoMusic( String url) {
        this.mediaUrl = url;
    }

    public MoMusic(String url, String dataUrl) {
        this.mediaUrl = url;
        this.mediaDataUrl = dataUrl;
    }
    public MoMusic withDuration(int value) {
        this.duration = value;
        return this;
    }
    public MoMusic withLowBand(String url) {
        this.lowBandUrl = url;
        return this;
    }
    public MoMusic withLowBand(String url, String dataUrl) {
        this.lowBandUrl = url;
        this.lowBandDataUrl = dataUrl;
        return this;
    }

    @Override
    public int type() {
        return TYPE_MUSIC;
    }

}