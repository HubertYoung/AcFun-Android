package com.hubertyoung.component.acfunvideo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class CommentParent {
    @SerializedName( "commentCount")
    public int commentCount;
    @SerializedName("hotComments")
    public List<CommentRoot> hotComments;
    @SerializedName("pcursor")
    public String pcursor;
    @SerializedName("rootComments")
    public List<CommentRoot> rootComments;
    @SerializedName("subCommentsMap")
    public Map<String, CommentChild> subCommentsMap;
}
