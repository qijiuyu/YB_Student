package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SchoolBean extends BaseBean {

    private List<School> data;

    public List<School> getData() {
        return data;
    }

    public void setData(List<School> data) {
        this.data = data;
    }

    public static class School implements Serializable{
        private int id;
        private String name;

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
    }
}
