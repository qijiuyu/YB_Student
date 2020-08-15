package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddResumeSpecialty implements Serializable {

    private String language;
    private String master;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
