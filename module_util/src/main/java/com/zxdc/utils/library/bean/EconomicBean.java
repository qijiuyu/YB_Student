package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class EconomicBean extends BaseBean {

    private List<Economic> data;

    public List<Economic> getData() {
        return data;
    }

    public void setData(List<Economic> data) {
        this.data = data;
    }

    public static class Economic implements Serializable{
        private int id;
        private String name;
        private boolean isSelect=false;

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

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
