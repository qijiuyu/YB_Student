package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.UploadPic;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class SettingP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public SettingP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
    }

    /**
     * 上传用户头像
     */
    public void uploadImg(List<FileBean> list){
        DialogUtil.showProgress(activity,"上传中");
        HttpMethod.updatephotoimg(list, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final UploadPic uploadPic= (UploadPic) object;
                if(uploadPic.isSussess()){

                    face.uploadSuccess(uploadPic.getData());

                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(uploadPic.getDesc());
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
     * 变更手机号
     */
    public void updatePhone(String code,String ecode,String phone){
        DialogUtil.showProgress(activity,"变更中");
        HttpMethod.updatePhone(code, ecode, phone, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){

                    face2.updatePhone();
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
        void uploadSuccess(String imgPath);
    }

    public interface Face2{
        void updatePhone();
    }
}
