package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 获取email验证码
 */
public class SendEmailP {

    private Activity activity;
    private Face face;

    public SendEmailP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 发送email验证信息
     */
    public void sendbindemail(String type,String email){
        DialogUtil.showProgress(activity,"发送中");
        HttpMethod.sendbindemail(type, email, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.sendbindemail();

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
        void sendbindemail();
    }
}
