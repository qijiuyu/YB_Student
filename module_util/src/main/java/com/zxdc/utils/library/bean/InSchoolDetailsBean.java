package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class InSchoolDetailsBean extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{
        private String content;
        private String descriptionfile;
        private String dnum;
        private int id;
        private int inschoolid;
        private String remarks;
        private int ruleid;
        private String schoolreport;
        private int status;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescriptionfile() {
            return descriptionfile;
        }

        public void setDescriptionfile(String descriptionfile) {
            this.descriptionfile = descriptionfile;
        }

        public String getDnum() {
            return dnum;
        }

        public void setDnum(String dnum) {
            this.dnum = dnum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInschoolid() {
            return inschoolid;
        }

        public void setInschoolid(int inschoolid) {
            this.inschoolid = inschoolid;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getRuleid() {
            return ruleid;
        }

        public void setRuleid(int ruleid) {
            this.ruleid = ruleid;
        }

        public String getSchoolreport() {
            return schoolreport;
        }

        public void setSchoolreport(String schoolreport) {
            this.schoolreport = schoolreport;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
