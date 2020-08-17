package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddEducation implements Serializable {

    private String type;//学校类型
    private String pcode;//省
    private String pname;
    private String ccode;//市
    private String cname;
    private String acode;//区
    private String aname;
    private String school;//所在学校
    private String time;//入学时间
    private String faculty;//院系名称
    private String specialty;//专业名称

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
