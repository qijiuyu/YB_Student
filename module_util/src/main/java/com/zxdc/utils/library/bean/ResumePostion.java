package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class ResumePostion extends BaseBean {

    private List<Position> data;

    public List<Position> getData() {
        return data;
    }

    public void setData(List<Position> data) {
        this.data = data;
    }

    public static class Position implements Serializable{
        private int positionId;
        private String positionName;
        private String positionLocation="";
        private String positionInfo="";
        private int eduReq;
        private int industrytype;
        private int selectId;//选中的id

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getPositionLocation() {
            return positionLocation;
        }

        public void setPositionLocation(String positionLocation) {
            this.positionLocation = positionLocation;
        }

        public String getPositionInfo() {
            return positionInfo;
        }

        public void setPositionInfo(String positionInfo) {
            this.positionInfo = positionInfo;
        }

        public int getEduReq() {
            return eduReq;
        }

        public void setEduReq(int eduReq) {
            this.eduReq = eduReq;
        }

        public int getIndustrytype() {
            return industrytype;
        }

        public void setIndustrytype(int industrytype) {
            this.industrytype = industrytype;
        }

        public int getSelectId() {
            return selectId;
        }

        public void setSelectId(int selectId) {
            this.selectId = selectId;
        }
    }
}
