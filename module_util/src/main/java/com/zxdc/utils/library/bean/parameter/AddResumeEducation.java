package com.zxdc.utils.library.bean.parameter;

import com.zxdc.utils.library.bean.Address;

import java.io.Serializable;
import java.util.List;

public class AddResumeEducation implements Serializable {

    private int id;

    private List<ObjectBean> learningExperiencePOJOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ObjectBean> getLearningExperiencePOJOS() {
        return learningExperiencePOJOS;
    }

    public void setLearningExperiencePOJOS(List<ObjectBean> learningExperiencePOJOS) {
        this.learningExperiencePOJOS = learningExperiencePOJOS;
    }

    public static class ObjectBean implements Serializable{
        private int ftype=1;
        private int ltype=0;
        private int mtype=1;
        private int type;
        private String admissiontime;
        private int education;
        private String faculty;
        private int facultyid;
        private String grades;
        private int id;
        private String major;
        private int majorid;
        private Address region;
        private int sid;

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public int getLtype() {
            return ltype;
        }

        public void setLtype(int ltype) {
            this.ltype = ltype;
        }

        public int getMtype() {
            return mtype;
        }

        public void setMtype(int mtype) {
            this.mtype = mtype;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAdmissiontime() {
            return admissiontime;
        }

        public void setAdmissiontime(String admissiontime) {
            this.admissiontime = admissiontime;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
            this.education = education;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public int getFacultyid() {
            return facultyid;
        }

        public void setFacultyid(int facultyid) {
            this.facultyid = facultyid;
        }

        public String getGrades() {
            return grades;
        }

        public void setGrades(String grades) {
            this.grades = grades;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getMajorid() {
            return majorid;
        }

        public void setMajorid(int majorid) {
            this.majorid = majorid;
        }

        public Address getRegion() {
            return region;
        }

        public void setRegion(Address region) {
            this.region = region;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }
    }
}
