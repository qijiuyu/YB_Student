package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class DeclareDetailsBean extends BaseBean {

    private DetailsBean data;

    public DetailsBean getData() {
        return data;
    }

    public void setData(DetailsBean data) {
        this.data = data;
    }

    public static class DetailsBean implements Serializable{

        private String admissionimg;
        private String bookimgf;
        private String bookimgz;
        private String[] economic;
        private String idcardimgf;
        private String idcardimgz;
        private String relatedimg;

        public String getAdmissionimg() {
            return admissionimg;
        }

        public void setAdmissionimg(String admissionimg) {
            this.admissionimg = admissionimg;
        }

        public String getBookimgf() {
            return bookimgf;
        }

        public void setBookimgf(String bookimgf) {
            this.bookimgf = bookimgf;
        }

        public String getBookimgz() {
            return bookimgz;
        }

        public void setBookimgz(String bookimgz) {
            this.bookimgz = bookimgz;
        }

        public String[] getEconomic() {
            return economic;
        }

        public void setEconomic(String[] economic) {
            this.economic = economic;
        }

        public String getIdcardimgf() {
            return idcardimgf;
        }

        public void setIdcardimgf(String idcardimgf) {
            this.idcardimgf = idcardimgf;
        }

        public String getIdcardimgz() {
            return idcardimgz;
        }

        public void setIdcardimgz(String idcardimgz) {
            this.idcardimgz = idcardimgz;
        }

        public String getRelatedimg() {
            return relatedimg;
        }

        public void setRelatedimg(String relatedimg) {
            this.relatedimg = relatedimg;
        }
    }
}
