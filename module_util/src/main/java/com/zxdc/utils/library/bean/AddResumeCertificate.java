package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 证书
 */
public class AddResumeCertificate implements Serializable {

    private String time;
    private String name;
    private String memo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
