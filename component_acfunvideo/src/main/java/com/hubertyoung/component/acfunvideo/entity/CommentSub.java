package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentSub extends CommentSend implements Serializable {
   @SerializedName("avatarFrame")
    public int avatarFrame;
   @SerializedName("isLiked")
    public boolean isLiked;
   @SerializedName("likeCount")
    public int likeCount;
   @SerializedName("likeCountFormat")
    public String likeCountFormat;
   @SerializedName("sourceId")
    public int sourceId;
   @SerializedName("sourceType")
    public int sourceType;
   @SerializedName("subCommentCount")
    public int subCommentCount;
   @SerializedName("subCommentCountFormat")
    public String subCommentCountFormat;
}
