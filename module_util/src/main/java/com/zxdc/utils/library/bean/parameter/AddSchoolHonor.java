package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class AddSchoolHonor implements Serializable {

    private int id;
    private List<ObjectBean> inSchoolHonorPOJOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ObjectBean> getInSchoolHonorPOJOS() {
        return inSchoolHonorPOJOS;
    }

    public void setInSchoolHonorPOJOS(List<ObjectBean> inSchoolHonorPOJOS) {
        this.inSchoolHonorPOJOS = inSchoolHonorPOJOS;
    }

    public static class ObjectBean implements Serializable{

        private String acquisitionTime;
        private String level;
        private String prize;

        public ObjectBean(String acquisitionTime, String prize, String level) {
            this.acquisitionTime = acquisitionTime;
            this.level = level;
            this.prize = prize;
        }

        public String getAcquisitionTime() {
            return acquisitionTime;
        }

        public void setAcquisitionTime(String acquisitionTime) {
            this.acquisitionTime = acquisitionTime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPrize() {
            return prize;
        }

        public void setPrize(String prize) {
            this.prize = prize;
        }
    }
}
