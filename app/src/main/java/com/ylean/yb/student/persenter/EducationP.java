package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class EducationP {

    private Activity activity;
    private Face face;

    public EducationP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
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


    public interface Face{
        void addSuccess();
    }
}
