package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSchoolPosition implements Serializable {

    private int id;
    private List<ObjectBean> schoolDutiesPOJOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ObjectBean> getSchoolDutiesPOJOS() {
        return schoolDutiesPOJOS;
    }

    public void setSchoolDutiesPOJOS(List<ObjectBean> schoolDutiesPOJOS) {
        this.schoolDutiesPOJOS = schoolDutiesPOJOS;
    }

    public static class ObjectBean implements Serializable{

        private String name;
        private String description;
        private String times;

        public ObjectBean(String name, String description, String times) {
            this.name = name;
            this.description = description;
            this.times = times;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }
}
