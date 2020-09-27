package com.zxdc.utils.library.bean.parameter;

import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.Salary;

import java.io.Serializable;
import java.util.List;

public class JobIntention implements Serializable {

    private String arrivalTime;
    private int dType;
    private Salary expectedCapital;
    private int id;
    private String introduce;
    private List<Industry> jobIndustryPOJOS;
    private List<Position> jobPositionTypePOJOS;
    private int status;
    private Address workPlace;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getdType() {
        return dType;
    }

    public void setdType(int dType) {
        this.dType = dType;
    }

    public Salary getExpectedCapital() {
        return expectedCapital;
    }

    public void setExpectedCapital(Salary expectedCapital) {
        this.expectedCapital = expectedCapital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Industry> getJobIndustryPOJOS() {
        return jobIndustryPOJOS;
    }

    public void setJobIndustryPOJOS(List<Industry> jobIndustryPOJOS) {
        this.jobIndustryPOJOS = jobIndustryPOJOS;
    }

    public List<Position> getJobPositionTypePOJOS() {
        return jobPositionTypePOJOS;
    }

    public void setJobPositionTypePOJOS(List<Position> jobPositionTypePOJOS) {
        this.jobPositionTypePOJOS = jobPositionTypePOJOS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Address getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Address workPlace) {
        this.workPlace = workPlace;
    }


    public static class Industry implements Serializable{
        private int industryTypeId;
        private String industryTypeName;

        public int getIndustryTypeId() {
            return industryTypeId;
        }

        public void setIndustryTypeId(int industryTypeId) {
            this.industryTypeId = industryTypeId;
        }

        public String getIndustryTypeName() {
            return industryTypeName;
        }

        public void setIndustryTypeName(String industryTypeName) {
            this.industryTypeName = industryTypeName;
        }
    }


    public static class Position implements Serializable{
        private int positionTypeId;

        public int getPositionTypeId() {
            return positionTypeId;
        }

        public void setPositionTypeId(int positionTypeId) {
            this.positionTypeId = positionTypeId;
        }
    }
}
