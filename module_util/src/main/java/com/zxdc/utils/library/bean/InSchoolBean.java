package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class InSchoolBean extends BaseBean {

    private List<InSchool> data;

    public List<InSchool> getData() {
        return data;
    }

    public void setData(List<InSchool> data) {
        this.data = data;
    }

    public static class InSchool implements Serializable{

        private int applyid;//申报id
        private String content;//在校情况说明
        private int id;
        private String name;//在校情况名称
        private int checkstatus;//审核状态 0未提交（需要学生提交） 2-待提交 3-已提交（待审核） 4-驳回 5-通过
        private String createtime;
        private int ruleid;//在校情况规则id
        private int schoolid;
        private int status;//在校情况状态 未提交（0） 在校(1), 违纪(2), 休学(3),参军(4), 退学(5), 死亡(6), 其他(7)
        private int type;//提交类型 1-学校提交 2-学生提交 3-基金会提交

        public int getApplyid() {
            return applyid;
        }

        public void setApplyid(int applyid) {
            this.applyid = applyid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getRuleid() {
            return ruleid;
        }

        public void setRuleid(int ruleid) {
            this.ruleid = ruleid;
        }

        public int getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(int schoolid) {
            this.schoolid = schoolid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
