package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class ReissueAuditBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        private String bname;
        private String bnum;
        private String createtime;
        private String fdate;
        private int id;
        private double money;
        private String remarks;
        private int rid;
        private String scoolname;
        private String statustext;
        private int type;

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getBnum() {
            return bnum;
        }

        public void setBnum(String bnum) {
            this.bnum = bnum;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getFdate() {
            return fdate;
        }

        public void setFdate(String fdate) {
            this.fdate = fdate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getScoolname() {
            return scoolname;
        }

        public void setScoolname(String scoolname) {
            this.scoolname = scoolname;
        }

        public String getStatustext() {
            return statustext;
        }

        public void setStatustext(String statustext) {
            this.statustext = statustext;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
