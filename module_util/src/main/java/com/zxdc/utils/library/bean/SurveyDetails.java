package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class SurveyDetails extends BaseBean {

    private Details data;

    public Details getData() {
        return data;
    }

    public void setData(Details data) {
        this.data = data;
    }

    public static class Details implements Serializable{
        private int id;
        private String name;
        private List<Ques> queslist;

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

        public List<Ques> getQueslist() {
            return queslist;
        }

        public void setQueslist(List<Ques> queslist) {
            this.queslist = queslist;
        }
    }


    public static class Ques implements Serializable{
        private int quesid;
        private String title;
        private int type;
        private List<Ans> anslist;
        //选中的答案
        private int selectAnsid;

        public int getQuesid() {
            return quesid;
        }

        public void setQuesid(int quesid) {
            this.quesid = quesid;
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

        public List<Ans> getAnslist() {
            return anslist;
        }

        public void setAnslist(List<Ans> anslist) {
            this.anslist = anslist;
        }

        public int getSelectAnsid() {
            return selectAnsid;
        }

        public void setSelectAnsid(int selectAnsid) {
            this.selectAnsid = selectAnsid;
        }
    }


    public static class Ans implements Serializable{
        private int ansid;
        private String optionvalue;
        private String content;

        public int getAnsid() {
            return ansid;
        }

        public void setAnsid(int ansid) {
            this.ansid = ansid;
        }

        public String getOptionvalue() {
            return optionvalue;
        }

        public void setOptionvalue(String optionvalue) {
            this.optionvalue = optionvalue;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }


}
