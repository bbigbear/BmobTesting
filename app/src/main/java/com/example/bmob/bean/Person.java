package com.example.bmob.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by bear on 2016/5/14.
 */
public class Person extends BmobObject{
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
