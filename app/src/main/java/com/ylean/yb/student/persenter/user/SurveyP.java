package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class SurveyP {

    private Activity activity;
    private Face face;
    private Face2 face2;


    public SurveyP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public SurveyP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }


    /**
     * 获取问卷列表
     */
    public void getSurveyList(int page){
        HttpMethod.getSurveyList(page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final SurveyBean surveyBean= (SurveyBean) object;
                if(surveyBean.isSussess()){

                    face.getSurveyList(surveyBean.getData());

                }else{
                    ToastUtil.showLong(surveyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取问卷详情
     */
    public void getSurveyDetails(int id){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getSurveyDetails(id, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{

        void getSurveyList(List<SurveyBean.Survey> list);
    }

    public interface Face2{

    }
}
