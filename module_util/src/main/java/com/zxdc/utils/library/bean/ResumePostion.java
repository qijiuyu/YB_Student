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
        private int id;
        private String name;
        private String gradationCode="";
        private int status;
        private int selectId;//选中的id

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGradationCode() {
            return gradationCode;
        }

        public void setGradationCode(String gradationCode) {
            this.gradationCode = gradationCode;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSelectId() {
            return selectId;
        }

        public void setSelectId(int selectId) {
            this.selectId = selectId;
        }
    }
}
