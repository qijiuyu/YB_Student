package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class AddFamily implements Serializable {

    private String relation;
    private String relationname;
    private String whethersupport;
    private String name;
    private String company;
    private String occupation;
    private String incomesource;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    public String getWhethersupport() {
        return whethersupport;
    }

    public void setWhethersupport(String whethersupport) {
        this.whethersupport = whethersupport;
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
