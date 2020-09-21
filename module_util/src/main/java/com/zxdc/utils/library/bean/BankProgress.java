package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class BankProgress extends BaseBean {

    private List<Progress> data;

    public List<Progress> getData() {
        return data;
    }

    public void setData(List<Progress> data) {
        this.data = data;
    }

    public static class Progress implements Serializable{
        private String createtime;
        private String jvalue;
        private int status;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getJvalue() {
            return jvalue;
        }

        public void setJvalue(String jvalue) {
            this.jvalue = jvalue;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
