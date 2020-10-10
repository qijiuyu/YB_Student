package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class IssueRecordBean extends BaseBean {

    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

    public static class ListBean implements Serializable{
        private int id;
        private String bknum;
        private String bname;
        private String createtime;
        private double money;
        private String reason;
        private String sname;
        private int status;//状态 发放中(2), 已发放(3), 银行发放失败(4), 卡号错误发放失败(5), 暂停发放(6),补发(8)
        private int years;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBknum() {
            return bknum;
        }

        public void setBknum(String bknum) {
            this.bknum = bknum;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getYears() {
            return years;
        }

        public void setYears(int years) {
            this.years = years;
        }
    }
}
