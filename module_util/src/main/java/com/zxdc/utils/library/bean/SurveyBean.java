package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SurveyBean extends BaseBean {

    private List<Survey> data;

    public List<Survey> getData() {
        return data;
    }

    public void setData(List<Survey> data) {
        this.data = data;
    }

    public static class Survey implements Serializable{
        private int id;
        private String title;
        private int quescount;
        private int responsecount;
        private int status;
        private String createtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getQuescount() {
            return quescount;
        }

        public void setQuescount(int quescount) {
            this.quescount = quescount;
        }

        public int getResponsecount() {
            return responsecount;
        }

        public void setResponsecount(int responsecount) {
            this.responsecount = responsecount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
