package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class DictBean extends BaseBean {

    private List<Dict> data;

    public List<Dict> getData() {
        return data;
    }

    public void setData(List<Dict> data) {
        this.data = data;
    }

    public static class Dict implements Serializable{
        private int id;
        private String name;
        private int pid;
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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getSelectId() {
            return selectId;
        }

        public void setSelectId(int selectId) {
            this.selectId = selectId;
        }
    }
}
