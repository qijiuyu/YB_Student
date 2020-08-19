package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class FacultyBean extends BaseBean {

    private List<Faculty> data;

    public List<Faculty> getData() {
        return data;
    }

    public void setData(List<Faculty> data) {
        this.data = data;
    }

    public static class Faculty implements Serializable{
        private int id;
        private String code;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
