package me.chenfuduo.networkingmodels.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MyWeibo {

    private String text;

    private String avatarUrl;

    private String name;

    public MyWeibo(String text, String avatarUrl, String name) {
        this.text = text;
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
