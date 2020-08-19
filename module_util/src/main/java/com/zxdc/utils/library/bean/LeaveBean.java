package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class LeaveBean extends BaseBean{

    private List<Leave> data;

    public List<Leave> getData() {
        return data;
    }

    public void setData(List<Leave> data) {
        this.data = data;
    }

    public static class Leave implements Serializable{
        private int id;
        private String createtime;
        private String message;
        private int type;//类型 0 为学生 1 基金会
        private List<Common> list;

        public List<Common> getList() {
            return list;
        }

        public void setList(List<Common> list) {
            this.list = list;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    public static class Common implements Serializable{
        private String message;
        private int id;
        private String createtime;
        private int type;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
