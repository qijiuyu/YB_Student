package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSpecialtyP implements Serializable {

    private int id;
    private List<DataBean> speciality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DataBean> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<DataBean> speciality) {
        this.speciality = speciality;
    }

    public static class DataBean implements Serializable{
        private String name;
        private String level;
        public DataBean(){}

        public DataBean(String name, String level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }


}
