package me.chenfuduo.myfragmentdemo.bean;

/**
 * Created by Administrator on 2015/7/30.
 */
public class Person {

    private int icon;

    private String name;

    public Person(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
