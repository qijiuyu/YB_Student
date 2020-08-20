package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class BatchDetails extends BaseBean {

    private Batch data;

    public Batch getData() {
        return data;
    }

    public void setData(Batch data) {
        this.data = data;
    }

    public static class Batch implements Serializable{
        private int applynum;
        private String createtime;
        private String endtime;
        private int id;
        private String img;
        private String name;
        private String remarks;
        private String starttime;
        private int status;
        private int type;
        private String factor;
        private int gbstatus;

        public int getGbstatus() {
            return gbstatus;
        }

        public void setGbstatus(int gbstatus) {
            this.gbstatus = gbstatus;
        }

        public int getApplynum() {
            return applynum;
        }

        public void setApplynum(int applynum) {
            this.applynum = applynum;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }
    }
}
