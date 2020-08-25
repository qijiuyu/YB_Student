package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class InSchoolP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public InSchoolP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public InSchoolP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }

    /**
     * 获得在校情况列表
     */
    public void getInSchoolList(int page){
        HttpMethod.getInSchoolList(page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final InSchoolBean inSchoolBean= (InSchoolBean) object;
                if(inSchoolBean.isSussess()){

                    face.getInSchoolList(inSchoolBean.getData());

                }else{
                    ToastUtil.showLong(inSchoolBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 编辑在校情况说明
     */
    public void updateInSchool(int did,int status,String schoolreport,String descriptionfile,final String content){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.updateInSchool(did, status, schoolreport, descriptionfile, content, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.updateSuccess();

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
        void getInSchoolList(List<InSchoolBean.InSchool> list);
    }

    public interface Face2{
        void updateSuccess();
    }

}