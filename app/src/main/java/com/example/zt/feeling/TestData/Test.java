package com.example.zt.feeling.TestData;

/**
 * Created by zt on 2016/5/5.
 */
public class Test {
    private int type;
    private String name;

    public Test() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Test(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
