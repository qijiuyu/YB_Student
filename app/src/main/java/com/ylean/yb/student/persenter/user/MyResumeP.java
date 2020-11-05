package com.ylean.yb.student.persenter.user;

import android.app.Activity;
import android.text.TextUtils;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.UploadResumeFile;
import com.zxdc.utils.library.bean.parameter.AddResumeEducation;
import com.zxdc.utils.library.bean.parameter.AddSchoolHonor;
import com.zxdc.utils.library.bean.parameter.AddSchoolPosition;
import com.zxdc.utils.library.bean.parameter.AddSpecialtyP;
import com.zxdc.utils.library.bean.parameter.JobIntention;
import com.zxdc.utils.library.bean.parameter.ResumeBase;
import com.zxdc.utils.library.bean.parameter.ResumeCertificate;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class MyResumeP {

    private Activity activity;
    private Face face;
    private Face2 face2;
    private Face3 face3;

    public MyResumeP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
    }

    public void setFace3(Face3 face3){
        this.face3=face3;
    }



    /**
     * 查询我的简历
     */
    public void getMyResume(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getMyResume(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final String msg= (String) object;
                if(TextUtils.isEmpty(msg)){
                    return;
                }
                try {
                    final JSONObject jsonObject=new JSONObject(msg);
                    if(jsonObject.getInt("code")==0){

                        final ResumeBean.Resume resume= (ResumeBean.Resume) JsonUtil.stringToObject(jsonObject.getString("data"),ResumeBean.Resume.class);
                        if(resume==null){
                            return;
                        }
                        face.getMyResume(resume);
                    }else{
                        ToastUtil.showLong(jsonObject.getString("desc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
    public void SaveOrUpdateCertificates(ResumeCertificate resumeCertificate){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.SaveOrUpdateCertificates(resumeCertificate, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    face2.onSuccess();
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
     * 新增或编辑简历信息(简历特长)
     */
    public void saveOrUpdateSpeciality(AddSpecialtyP addSpecialtyP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateSpeciality(addSpecialtyP, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
     * 新增或编辑简历信息(在校荣誉)
     */
    public void saveOrUpdateInSchoolHonor(AddSchoolHonor addSchoolHonor){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateInSchoolHonor(addSchoolHonor, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
     * 新增或编辑简历信息(校内职务)
     */
    public void saveOrUpdateSchoolDuties(AddSchoolPosition addSchoolPosition){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateSchoolDuties(addSchoolPosition, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
     * 新新增或编辑简历学习经历
     */
    public void saveOrUpdateLearnings(AddResumeEducation addResumeEducation){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateLearnings(addResumeEducation, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
     * 新增或编辑简历求职意向
     */
    public void saveOrUpdateJobIdea(JobIntention jobIntention){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateJobIdea(jobIntention, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
     * 新增或编辑简历基本信息
     */
    public void saveOrUpdateResumePerson(ResumeBase resumeBase){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveOrUpdateResumePerson(resumeBase, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face3.baseSuccess();

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
     * 编辑简历附件
     */
    public void uploadResumeFile(UploadResumeFile uploadResumeFile){
        DialogUtil.showProgress(activity,"附件上传中");
        HttpMethod.uploadResumeFile(uploadResumeFile, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.onSuccess();

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
        void getMyResume(ResumeBean.Resume resume);
    }

    public interface Face2{

       void onSuccess();
    }

    public interface Face3{
        void baseSuccess();
    }

}
