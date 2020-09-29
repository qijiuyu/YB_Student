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
        private String name;
        private String title;
        private String createtime;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
