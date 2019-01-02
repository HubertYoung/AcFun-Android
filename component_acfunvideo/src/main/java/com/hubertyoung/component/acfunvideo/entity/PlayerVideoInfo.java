package com.hubertyoung.component.acfunvideo.entity;


import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.entity.Video;

import java.io.Serializable;
import java.util.List;

public class PlayerVideoInfo implements Serializable {
    private boolean allowPlayWithMobileOnce;
    private String bangumiVideoCount;
    private int channelId;
    private int contentId;
    private String des;
    private String from;
    private String groupId;
    private boolean isEndBangumi;
    private boolean isHapame;
    private boolean isVideoDetailFrom;
    private int pid;
    private int playWay;
    private String releaseTime;
    private String reqId;
    private String shareUrl;
    private String title;
    private int type;
    private User uploaderData;
    private Video video;
    private String videoCover;
    private List<Video> videoList;
    private String videoTitle;

    public PlayerVideoInfo( Video video, int pid, int channelId, int contentId, int type, String videoTitle) {
        this.video = video;
        this.pid = pid;
        this.channelId = channelId;
        this.contentId = contentId;
        this.type = type;
        this.videoTitle = videoTitle;
    }

    public String getBangumiVideoCount() {
        return this.bangumiVideoCount;
    }

    public void setBangumiVideoCount(String str) {
        this.bangumiVideoCount = str;
    }

    public boolean isEndBangumi() {
        return this.isEndBangumi;
    }

    public void setIsEndBangumi(boolean z) {
        this.isEndBangumi = z;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int i) {
        this.pid = i;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public void setChannelId(int i) {
        this.channelId = i;
    }

    public int getContentId() {
        return this.contentId;
    }

    public void setContentId(int i) {
        this.contentId = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public int getPlayWay() {
        return this.playWay;
    }

    public void setPlayWay(int i) {
        this.playWay = i;
    }

    public User getUploaderData() {
        return this.uploaderData;
    }

    public void setUploaderData(User user) {
        this.uploaderData = user;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String str) {
        this.videoTitle = str;
    }

    public boolean isAllowPlayWithMobileOnce() {
        return this.allowPlayWithMobileOnce;
    }

    public void setAllowPlayWithMobileOnce(boolean z) {
        this.allowPlayWithMobileOnce = z;
    }

    public List<Video> getVideoList() {
        return this.videoList;
    }

    public void setVideoList(List<Video> list) {
        this.videoList = list;
    }

    public void setVideoCover(String str) {
        this.videoCover = str;
    }

    public String getVideoCover() {
        return this.videoCover;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getFrom() {
        return this.from;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String str) {
        this.des = str;
    }

    public boolean isHapame() {
        return this.isHapame;
    }

    public void setHapame(boolean z) {
        this.isHapame = z;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public String getReleaseTime() {
        return this.releaseTime;
    }

    public void setReleaseTime(String str) {
        this.releaseTime = str;
    }

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("PlayerVideoInfo{allowPlayWithMobileOnce=");
//        stringBuilder.append(this.allowPlayWithMobileOnce);
//        stringBuilder.append(", video=");
//        stringBuilder.append(this.video);
//        stringBuilder.append(", channelId=");
//        stringBuilder.append(this.channelId);
//        stringBuilder.append(", contentId=");
//        stringBuilder.append(this.contentId);
//        stringBuilder.append(", type=");
//        stringBuilder.append(this.type);
//        stringBuilder.append(", videoTitle='");
//        stringBuilder.append(this.videoTitle);
//        stringBuilder.append('\'');
//        stringBuilder.append(", playWay=");
//        stringBuilder.append(this.playWay);
//        stringBuilder.append(", isEndBangumi=");
//        stringBuilder.append(this.isEndBangumi);
//        stringBuilder.append(", bangumiVideoCount='");
//        stringBuilder.append(this.bangumiVideoCount);
//        stringBuilder.append('\'');
//        stringBuilder.append(", uploaderData=");
//        stringBuilder.append(this.uploaderData);
//        stringBuilder.append(", videoList=");
//        stringBuilder.append(this.videoList);
//        stringBuilder.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
//        return stringBuilder.toString();
//    }

    public void setVideoDetailFrom(boolean z) {
        this.isVideoDetailFrom = z;
    }

    public boolean isVideoDetailFrom() {
        return this.isVideoDetailFrom;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }
}
