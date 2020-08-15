package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddHonor implements Serializable {

    private String time;
    private String award;//奖项
    private String level;//级别

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
