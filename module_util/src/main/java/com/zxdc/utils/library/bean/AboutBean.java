package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AboutBean extends BaseBean {

    private About data;

    public About getData() {
        return data;
    }

    public void setData(About data) {
        this.data = data;
    }

    public static class About implements Serializable{
        private String title;
        private String content;

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
    }
}
