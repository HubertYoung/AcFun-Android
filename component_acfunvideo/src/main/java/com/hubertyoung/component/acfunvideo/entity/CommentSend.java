package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommentSend implements Serializable {
    @SerializedName("commentId")
    public String commentId;
    @SerializedName("content")
    public String content;
    @SerializedName("floor")
    public int floor;
    @SerializedName("headUrl")
    public List<CommentHeadUrl> headUrl;
    @SerializedName("replyTo")
    public int replyTo;
    @SerializedName("replyToUserName")
    public String replyToUserName;
    @SerializedName("timestamp")
    public Date timestamp;
    @SerializedName("userId")
    public int userId;
    @SerializedName("userName")
    public String userName;

    public String getHeadUrl() {
        return (this.headUrl == null || this.headUrl.size() <= 0) ? "" : ((CommentHeadUrl) this.headUrl.get(0)).url;
    }
}
