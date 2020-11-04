package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class NewsSingle extends BaseBean {

    private Single data;

    public Single getData() {
        return data;
    }

    public void setData(Single data) {
        this.data = data;
    }

    public static class Single implements Serializable{
        private int id;
        private String title;
        private String content;
        private String subtitle;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }
}
