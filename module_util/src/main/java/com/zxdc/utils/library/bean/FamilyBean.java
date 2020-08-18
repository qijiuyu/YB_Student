package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class FamilyBean extends BaseBean {

    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

    public static class ListBean implements Serializable{
        private int id;
        private int uid;
        private int relation;
        private String relationname;
        private int whethersupport;
        private String name;
        private String company;
        private String occupation;
        private String incomesource;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getRelationname() {
            return relationname;
        }

        public void setRelationname(String relationname) {
            this.relationname = relationname;
        }

        public int getWhethersupport() {
            return whethersupport;
        }

        public void setWhethersupport(int whethersupport) {
            this.whethersupport = whethersupport;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getIncomesource() {
            return incomesource;
        }

        public void setIncomesource(String incomesource) {
            this.incomesource = incomesource;
        }
    }
}
