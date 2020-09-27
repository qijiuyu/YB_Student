package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class DeliveryBean extends BaseBean {

    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

    public static class ListBean implements Serializable{
        private String name="";
        private int salary;
        private String positionInfo="";
        private String positionLocation="";
        private int numPerson;
        private int workYear;
        private int eduReq;
        private String eduReqName="";
        private String createTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getPositionInfo() {
            return positionInfo;
        }

        public void setPositionInfo(String positionInfo) {
            this.positionInfo = positionInfo;
        }

        public String getPositionLocation() {
            return positionLocation;
        }

        public void setPositionLocation(String positionLocation) {
            this.positionLocation = positionLocation;
        }

        public int getNumPerson() {
            return numPerson;
        }

        public void setNumPerson(int numPerson) {
            this.numPerson = numPerson;
        }

        public int getWorkYear() {
            return workYear;
        }

        public void setWorkYear(int workYear) {
            this.workYear = workYear;
        }

        public int getEduReq() {
            return eduReq;
        }

        public void setEduReq(int eduReq) {
            this.eduReq = eduReq;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getEduReqName() {
            return eduReqName;
        }

        public void setEduReqName(String eduReqName) {
            this.eduReqName = eduReqName;
        }
    }
}
