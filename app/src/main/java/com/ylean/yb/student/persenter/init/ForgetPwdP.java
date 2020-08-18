package com.ylean.yb.student.persenter.init;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;

public class ForgetPwdP {

    private Activity activity;
    private Face face;

    public ForgetPwdP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 找回密码第一步
     */
    public void findfirstpwd(String idnum){
        DialogUtil.showProgress(activity,"验证中");
        HttpMethod.findfirstpwd(idnum, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void onSuccess();
    }
}
