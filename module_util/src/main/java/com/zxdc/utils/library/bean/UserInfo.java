package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class UserInfo extends BaseBean {

    private UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

    public static class UserBean implements Serializable{

        private int id;
        private int ctype;
        private String idnum;
        private String name="";
        private String sex="";
        private String birthday="";
        private String address="";
        private String nation="";
        private String nationality="";
        private String photo="";
        private String education="";
        private String residenceaddress="";
        private String qq="";
        private String wechat="";
        private String validitystarttime="";
        private String validityendtime="";
        private boolean isbfchildren;
        private String idimgurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getIdnum() {
            return idnum;
        }

        public void setIdnum(String idnum) {
            this.idnum = idnum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getResidenceaddress() {
            return residenceaddress;
        }

        public void setResidenceaddress(String residenceaddress) {
            this.residenceaddress = residenceaddress;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getValiditystarttime() {
            return validitystarttime;
        }

        public void setValiditystarttime(String validitystarttime) {
            this.validitystarttime = validitystarttime;
        }

        public String getValidityendtime() {
            return validityendtime;
        }

        public void setValidityendtime(String validityendtime) {
            this.validityendtime = validityendtime;
        }

        public boolean isIsbfchildren() {
            return isbfchildren;
        }

        public void setIsbfchildren(boolean isbfchildren) {
            this.isbfchildren = isbfchildren;
        }

    }


    public static class CardImg implements Serializable{
        private String positive;
        private String back;

        public String getPositive() {
            return positive;
        }

        public void setPositive(String positive) {
            this.positive = positive;
        }

        public String getBack() {
            return back;
        }

        public void setBack(String back) {
            this.back = back;
        }
    }
}
