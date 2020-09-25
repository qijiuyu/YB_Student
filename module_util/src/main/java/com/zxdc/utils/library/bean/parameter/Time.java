package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class Time implements Serializable {
    private String start;
    private String end;

    public Time(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
