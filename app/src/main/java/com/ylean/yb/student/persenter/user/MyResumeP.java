package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class MyResumeP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public MyResumeP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public MyResumeP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }


    /**
     * 查询我的简历
     */
    public void getMyResume(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getMyResume(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ResumeBean resumeBean= (ResumeBean) object;
                if(resumeBean.isSussess()){

                    face.getMyResume(resumeBean.getData());

                }else{
                    ToastUtil.showLong(resumeBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 新增或编辑简历信息(简历证书)
     */
    public void SaveOrUpdateCertificates(String parameter){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.SaveOrUpdateCertificates(parameter, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getMyResume(ResumeBean.Resume resume);
    }

    public interface Face2{
        void SaveOrUpdateCertificates();
    }
}
