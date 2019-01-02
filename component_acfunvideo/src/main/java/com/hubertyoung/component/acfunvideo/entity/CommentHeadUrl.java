package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentHeadUrl implements Serializable {
    @SerializedName("cdn")
    public String cdn;
    @SerializedName("url")
    public String url;
    @SerializedName("urlPattern")
    public String urlPattern;
}
