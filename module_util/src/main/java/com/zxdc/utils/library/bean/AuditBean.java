package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class AuditBean extends BaseBean {

    private Audit data;

    public Audit getData() {
        return data;
    }

    public void setData(Audit data) {
        this.data = data;
    }

    public static class Audit implements Serializable{
        private int atype;//审核类型 0 教育局->基金会 1 学校->基金会 2直接基金会
        private String bname;
        private String code;
        private String createtime;
        private String reason;
        private String jxatime;//学校或者教育局审核时间
        private String jhatime;//基金会审核时间
        private int status;//状态 未审核(0), 学校审核通过(1), 学校审核不通过(2), 教育局审核通过(3), 教育局审核不通过(4), 基金会审核通过(5), 基金会审核不通过(6), 教育局驳回(7),\n基金会驳回(8), 学校驳回(9), 教育局提交(10), 学校提交(11)

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAtype() {
            return atype;
        }

        public void setAtype(int atype) {
            this.atype = atype;
        }

        public String getJxatime() {
            return jxatime;
        }

        public void setJxatime(String jxatime) {
            this.jxatime = jxatime;
        }

        public String getJhatime() {
            return jhatime;
        }

        public void setJhatime(String jhatime) {
            this.jhatime = jhatime;
        }
    }
}
