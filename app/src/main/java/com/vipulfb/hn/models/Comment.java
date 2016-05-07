package com.vipulfb.hn.models;

import java.io.Serializable;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class Comment extends MasterItem implements Serializable {
    private String parent;
    private String text;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
