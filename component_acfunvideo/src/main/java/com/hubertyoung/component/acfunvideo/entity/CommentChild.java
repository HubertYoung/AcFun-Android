package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentChild {
    @SerializedName("pcursor")
    public String pcursor;
    @SerializedName("subCommentCount")
    public int subCommentCount;
    @SerializedName("subComments")
    public List<CommentSub> subComments;
}
