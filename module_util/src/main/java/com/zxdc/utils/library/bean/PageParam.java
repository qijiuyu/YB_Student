package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class PageParam implements Serializable {

    private int page;
    private int size;

    public PageParam(){}

    public PageParam(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
