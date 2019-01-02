package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentRoot extends CommentSub implements Serializable {
    @SerializedName("subCommentCount")
    public int subCommentCount;
    @SerializedName("subCommentCountFormat")
    public String subCommentCountFormat;
}
