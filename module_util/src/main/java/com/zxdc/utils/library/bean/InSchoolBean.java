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
        private String endtime;//提交结束时间
        private int id;
        private String name;//在校情况名称
        private String starttime;//提交开始时间
        private int status;//审核状态 2-待提交 3-已提交（待审核） 4-驳回 5-通过
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

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
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

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
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
