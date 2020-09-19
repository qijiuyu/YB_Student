package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.EducationBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class EducationP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public EducationP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public EducationP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }


    /**
     * 获取学习经历集合
     */
    public void getEducationList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getEducationList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final EducationBean educationBean= (EducationBean) object;
                if(educationBean==null){
                    return;
                }
                if(educationBean.isSussess()){

                    face2.getEducationList(educationBean.getData());

                }else{
                    ToastUtil.showLong(educationBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 添加教育经历
     */
    public void addEducation(String learningexperiences){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.addEducation(learningexperiences, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.addSuccess();

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 删除教育经历
     */
    public void deleteEducation(final EducationBean.Education education){
        DialogUtil.showProgress(activity,"删除中");
        HttpMethod.deleteEducation(education.getId(), new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.deleteEducation(education);

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });

    }


    public interface Face{
        void addSuccess();
    }

    public interface Face2{

        void getEducationList(List<EducationBean.Education> list);

        void deleteEducation(EducationBean.Education education);
    }
}
