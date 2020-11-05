package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class UploadResumeFile implements Serializable {

    private int id;
    private String enclosure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }
}
