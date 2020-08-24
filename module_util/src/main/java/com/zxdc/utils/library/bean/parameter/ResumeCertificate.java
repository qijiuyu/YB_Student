package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;
import java.util.List;

public class ResumeCertificate implements Serializable {

    private int id;

    private List<DataBean> certificatesPOJOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DataBean> getCertificatesPOJOS() {
        return certificatesPOJOS;
    }

    public void setCertificatesPOJOS(List<DataBean> certificatesPOJOS) {
        this.certificatesPOJOS = certificatesPOJOS;
    }

    public static class DataBean implements Serializable{
        private int resumeId;
        private String acquisitionTime;
        private String name;
        private String remarks;

        public int getResumeId() {
            return resumeId;
        }

        public void setResumeId(int resumeId) {
            this.resumeId = resumeId;
        }

        public String getAcquisitionTime() {
            return acquisitionTime;
        }

        public void setAcquisitionTime(String acquisitionTime) {
            this.acquisitionTime = acquisitionTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }


}
