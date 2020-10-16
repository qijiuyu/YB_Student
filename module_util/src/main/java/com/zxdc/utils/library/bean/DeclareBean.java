package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class DeclareBean extends BaseBean {

    private List<Declare> data;

    public List<Declare> getData() {
        return data;
    }

    public void setData(List<Declare> data) {
        this.data = data;
    }

    public static class Declare implements Serializable{
        private int bdid;
        private int bdstatus;//申报状态 未审核(0), 学校审核通过(1), 学校审核不通过(2), 教育局审核通过(3), 教育局审核不通过(4), 基金会审核通过(5), 基金会审核不通过(6) , 教育局驳回(7), 基金会驳回(8), 学校驳回(9)
        private String createtime;
        private int gbid;
        private int gbstatus;//批次状态 未开始(0), 已开始(1), 已结束(2)
        private String img;
        private String siname;
        private int sitype;//学校类型 高中(0), 中职(1), 高职(2),大学(3)
        private String bname;

        public int getBdid() {
            return bdid;
        }

        public void setBdid(int bdid) {
            this.bdid = bdid;
        }

        public int getBdstatus() {
            return bdstatus;
        }

        public void setBdstatus(int bdstatus) {
            this.bdstatus = bdstatus;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getGbid() {
            return gbid;
        }

        public void setGbid(int gbid) {
            this.gbid = gbid;
        }

        public int getGbstatus() {
            return gbstatus;
        }

        public void setGbstatus(int gbstatus) {
            this.gbstatus = gbstatus;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSiname() {
            return siname;
        }

        public void setSiname(String siname) {
            this.siname = siname;
        }

        public int getSitype() {
            return sitype;
        }

        public void setSitype(int sitype) {
            this.sitype = sitype;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }
    }
}
