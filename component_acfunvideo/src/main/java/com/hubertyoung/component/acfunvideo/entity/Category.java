package com.hubertyoung.component.acfunvideo.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//@Table(name = "categorydb")
public class Category implements Serializable {
    public static final int TAG_RECENT_UPDATE = 421712;
    public static final int VISIBLE = 1;
//    @Column(name = "bid")
    private int bid;
    @SerializedName("id")
//    @Column(name = "cid")
    private int cid;
    @SerializedName("cover")
//    @Column(name = "cover")
    private String cover;
    @SerializedName("display")
//    @Column(name = "display")
    private int display;
//    @Column(isId = true, name = "id")
    private String id;
    @SerializedName("name")
//    @Column(name = "title")
    private String title;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getBid() {
        return this.bid;
    }

    public void setBid(int i) {
        this.bid = i;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int i) {
        this.cid = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String str) {
        this.cover = str;
    }

    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int i) {
        this.display = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category{cid=");
        stringBuilder.append(this.cid);
        stringBuilder.append(", title='");
        stringBuilder.append(this.title);
        stringBuilder.append('\'');
        stringBuilder.append(", cover='");
        stringBuilder.append(this.cover);
        stringBuilder.append('\'');
        // TODO: 2018/12/18  com.dd.plist.ASCIIPropertyListParser
//        stringBuilder.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        return stringBuilder.toString();
    }
}
