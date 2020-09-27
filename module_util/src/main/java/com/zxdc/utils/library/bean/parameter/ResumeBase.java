package com.zxdc.utils.library.bean.parameter;

import com.zxdc.utils.library.bean.Address;

import java.io.Serializable;

public class ResumeBase implements Serializable {

    private Address currentResidence;
    private int id;
    private String img;
    private String mail;
    private String phone;
    private String qq;
    private String wx;

    public Address getCurrentResidence() {
        return currentResidence;
    }

    public void setCurrentResidence(Address currentResidence) {
        this.currentResidence = currentResidence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }
}
