package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.AESUtil;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

/**
 * 获取验证码
 */
public class SendCodeP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public SendCodeP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
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


    /**
     * 发送email验证信息(根据身份证号码)
     */
    public void sendbindemailByNum(String type,String idnum){
        DialogUtil.showProgress(activity,"发送中");
        HttpMethod.sendbindemailByNum(type, idnum, new NetCallBack() {
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


    /**
     * 获取短信验证码
     * type：  0学生注册，  1学生登录   2变更手机号    3忘记密码
     */
    public void getSmsCode(String code,String phone,String type){
        DialogUtil.showProgress(activity,"验证码已发送，请注意查收");
        HttpMethod.getSmsCode(code, AESUtil.encrypt(phone), type, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.getSmsCode();

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
     * 发送短信(根据身份证号码)
     */
    public void getSmsCodeByNum(String idnum,String type){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.getSmsCodeByNum(idnum, type, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.getSmsCode();

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

    public interface Face2{
        void getSmsCode();
    }
}
