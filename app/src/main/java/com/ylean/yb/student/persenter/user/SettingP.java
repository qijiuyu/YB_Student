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

    public SettingP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
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


    public interface Face{
        void uploadSuccess(String imgPath);
    }
}
