package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class ReceivablesheadBean extends BaseBean{

    private HeadBean data;

    public HeadBean getData() {
        return data;
    }

    public void setData(HeadBean data) {
        this.data = data;
    }

    public static class HeadBean implements Serializable{
        private int bcount;
        private int bid;
        private String bname;
        private int dcount;
        private double dmoney;
        private int id;
        private double money;
        private int ycount;
        private int years;
        private double ymoney;

        public int getBcount() {
            return bcount;
        }

        public void setBcount(int bcount) {
            this.bcount = bcount;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public int getDcount() {
            return dcount;
        }

        public void setDcount(int dcount) {
            this.dcount = dcount;
        }

        public double getDmoney() {
            return dmoney;
        }

        public void setDmoney(double dmoney) {
            this.dmoney = dmoney;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getYcount() {
            return ycount;
        }

        public void setYcount(int ycount) {
            this.ycount = ycount;
        }

        public int getYears() {
            return years;
        }

        public void setYears(int years) {
            this.years = years;
        }

        public double getYmoney() {
            return ymoney;
        }

        public void setYmoney(double ymoney) {
            this.ymoney = ymoney;
        }
    }
}
