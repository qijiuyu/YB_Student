package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddEducation implements Serializable {

    private int type;//学校类型
    private Region region;
    private int sid;//所在学校
    private int facultyid;//院系
    private int majorid;//专业
    private String admissiontime;//入学时间
    private int ftype=1;//院系类型 1 是选择 2 是手填
    private int mtype=1;//专业类型 1 是选择 2是手填

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(int facultyid) {
        this.facultyid = facultyid;
    }

    public int getMajorid() {
        return majorid;
    }

    public void setMajorid(int majorid) {
        this.majorid = majorid;
    }

    public String getAdmissiontime() {
        return admissiontime;
    }

    public void setAdmissiontime(String admissiontime) {
        this.admissiontime = admissiontime;
    }


    public static class Region implements Serializable{
        private String pcode;//省
        private String pname;
        private String ccode;//市
        private String cname;
        private String acode;//区
        private String aname;

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getCcode() {
            return ccode;
        }

        public void setCcode(String ccode) {
            this.ccode = ccode;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }
}
