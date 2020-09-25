package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class GetDeliveryRecord implements Serializable {

    private int page;
    private int size;
    private int resumeId;

    public GetDeliveryRecord(int page, int size, int resumeId) {
        this.page = page;
        this.size = size;
        this.resumeId = resumeId;
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

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }
}
