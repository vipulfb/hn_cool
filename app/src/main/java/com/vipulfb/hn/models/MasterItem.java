package com.vipulfb.hn.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class MasterItem {
    private String by;
    private String id;
    @SerializedName("kids")
    private ArrayList<String> commentsIdList = new ArrayList<>();
    private String time;
    private String type;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getCommentsIdList() {
        return commentsIdList;
    }

    public void setCommentsIdList(ArrayList<String> commentsIdList) {
        this.commentsIdList = commentsIdList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
