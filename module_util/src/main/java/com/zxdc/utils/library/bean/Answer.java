package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class Answer implements Serializable {

    private int quesid;
    private String value;//选项值A,B,C 或者是 输入类型问题的回答

    public int getQuesid() {
        return quesid;
    }

    public void setQuesid(int quesid) {
        this.quesid = quesid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
