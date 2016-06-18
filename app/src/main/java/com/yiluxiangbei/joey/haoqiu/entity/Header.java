package com.yiluxiangbei.joey.haoqiu.entity;

/**
 * Created by admin on 2016/2/23.
 */
public class Header {

    private String saiCheng1;
    private String saiCheng2;

    public Header() {
    }

    public Header(String saiCheng1, String saiCheng2) {
        this.saiCheng1 = saiCheng1;
        this.saiCheng2 = saiCheng2;
    }

    public String getSaiCheng1() {
        return saiCheng1;
    }

    public void setSaiCheng1(String saiCheng1) {
        this.saiCheng1 = saiCheng1;
    }

    public String getSaiCheng2() {
        return saiCheng2;
    }

    public void setSaiCheng2(String saiCheng2) {
        this.saiCheng2 = saiCheng2;
    }

    @Override
    public String toString() {
        return "Header{" +
                "saiCheng1='" + saiCheng1 + '\'' +
                ", saiCheng2='" + saiCheng2 + '\'' +
                '}';
    }
}
