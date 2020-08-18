package com.ylean.yb.student.persenter.init;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class ForgetPwdP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public ForgetPwdP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public ForgetPwdP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }


    /**
     * 找回密码第一步
     */
    public void findfirstpwd(String idnum){
        DialogUtil.showProgress(activity,"验证中");
        HttpMethod.findfirstpwd(idnum, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ForgetPwd forgetPwd= (ForgetPwd) object;
                if(forgetPwd.isSussess()){

                    face.onSuccess(forgetPwd);

                }else{
                    ToastUtil.showLong(forgetPwd.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 设置新登录密码
     */
    public void findpwd(String idnum,String code,String pwd,String rpwd,int type){
        DialogUtil.showProgress(activity,"修改中");
        HttpMethod.findpwd(idnum, code, pwd, rpwd, type, new NetCallBack() {
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
        void onSuccess(ForgetPwd forgetPwd);
    }


    public interface Face2{
        void onSuccess();
    }
}
