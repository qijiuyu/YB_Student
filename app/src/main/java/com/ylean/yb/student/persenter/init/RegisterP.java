package com.ylean.yb.student.persenter.init;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class RegisterP {

    private Activity activity;
    private Face face;
    private Face2 face2;
    private Face3 face3;

    public RegisterP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public RegisterP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }

    public RegisterP(Activity activity,Face3 face3){
        this.activity=activity;
        this.face3=face3;
    }

    /**
     * 注册第一步
     */
    public void register(String code, String idcardno, String phone, String pwd, List<FileBean> list){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.register1(code, idcardno, phone, pwd, list, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final Register userInfo= (Register) object;
                if(userInfo.isSussess()){
                    face.onSuccess(userInfo);
                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(userInfo.getDesc());
                        }
                    });
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 注册第二步
     */
    public void register2(String ucphone,String address,String qq,String residenceaddress,String uctel,String token,String wechat){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.saveUser(ucphone, address, qq, residenceaddress, uctel, token, wechat, new NetCallBack() {
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
     * 学生注册第三步
     */
    public void bindingEmail(String code,String email,String token){
        DialogUtil.showProgress(activity,"绑定中");
        HttpMethod.bindingEmail(code, email,token, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face3.onSuccess();

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
        void onSuccess(Register userInfo);
    }

    public interface Face2{
        void onSuccess();
    }

    public interface Face3{
        void onSuccess();
    }
}
