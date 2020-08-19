package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class NewsBean extends BaseBean {

    private List<News> data;

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }

    public static class News implements Serializable{
        private int id;
        private String message;
        private String createtime;
        private int status;//状态 0 未阅读 1已阅读
        private String title;
        private int type;//站内信类型 1银行卡 2申报 3补发 4在校等（后期会调整 主要用于跳转页面判断）
        private int rid;//关联id 跳转页面 参数

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }
    }
}
