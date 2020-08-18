package com.ylean.yb.student.persenter.init;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class LoginP {

    private Activity activity;
    private Face face;

    public LoginP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 登录
     * @param name
     * @param pwd
     */
    public void login(String name,String pwd){
        DialogUtil.showProgress(activity,"登录中");
        HttpMethod.login(name, pwd, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.onSuccess(baseBean);

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
        void onSuccess(BaseBean baseBean);
    }
}
