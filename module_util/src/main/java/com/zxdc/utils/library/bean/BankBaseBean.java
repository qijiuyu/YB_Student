package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class BankBaseBean extends BaseBean {

    private BankBase data;

    public BankBase getData() {
        return data;
    }

    public void setData(BankBase data) {
        this.data = data;
    }

    public static class BankBase implements Serializable{
        private String bankimgurl;
        private String bankname;
        private String banknum;
        private int bankstatus;//银行卡状态 未提交(0), 已提交(1), 未开卡(2), 已开卡(3), 已邮寄(4), 正在使用(5), 已更改(6), 失败(7), 开卡中(8)
        private int id;
        private String idnum;
        private String phone;
        private String uname;

        public String getBankimgurl() {
            return bankimgurl;
        }

        public void setBankimgurl(String bankimgurl) {
            this.bankimgurl = bankimgurl;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getBanknum() {
            return banknum;
        }

        public void setBanknum(String banknum) {
            this.banknum = banknum;
        }

        public int getBankstatus() {
            return bankstatus;
        }

        public void setBankstatus(int bankstatus) {
            this.bankstatus = bankstatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdnum() {
            return idnum;
        }

        public void setIdnum(String idnum) {
            this.idnum = idnum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }
    }
}
