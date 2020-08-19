package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class SchoolP {

    private Activity activity;
    private Face face;

    public SchoolP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取所有学校
     */
    public void getSchoolList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getSchoolList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final SchoolBean schoolBean= (SchoolBean) object;
                if(schoolBean.isSussess()){

                    face.getSchoolList(schoolBean.getData());

                }else{
                    ToastUtil.showLong(schoolBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取所有院系
     */
    public void getFacultyList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getFacultyList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final FacultyBean facultyBean= (FacultyBean) object;
                if(facultyBean.isSussess()){

                    face.getFacultyList(facultyBean.getData());

                }else{
                    ToastUtil.showLong(facultyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 获取所有专业
     */
    public void getSpecialtyList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getSpecialtyList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final FacultyBean facultyBean= (FacultyBean) object;
                if(facultyBean.isSussess()){

                    face.getSpecialtyList(facultyBean.getData());

                }else{
                    ToastUtil.showLong(facultyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getSchoolList(List<SchoolBean.School> list);

        void getFacultyList(List<FacultyBean.Faculty> list);

        void getSpecialtyList(List<FacultyBean.Faculty> list);
    }
}
