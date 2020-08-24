package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class ActivityNum extends BaseBean {

    private NumBean data;

    public NumBean getData() {
        return data;
    }

    public void setData(NumBean data) {
        this.data = data;
    }

    public static class NumBean implements Serializable{

        private int activityNum;
        private int noApply;
        private int totalPublicWelfare;

        public int getActivityNum() {
            return activityNum;
        }

        public void setActivityNum(int activityNum) {
            this.activityNum = activityNum;
        }

        public int getNoApply() {
            return noApply;
        }

        public void setNoApply(int noApply) {
            this.noApply = noApply;
        }

        public int getTotalPublicWelfare() {
            return totalPublicWelfare;
        }

        public void setTotalPublicWelfare(int totalPublicWelfare) {
            this.totalPublicWelfare = totalPublicWelfare;
        }
    }
}
