package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.UploadFile;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class UploadFileP {

    private Activity activity;
    private Face face;

    public UploadFileP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 上传文件
     */
    public void uploadFile(int relationtype,List<FileBean> list){
        DialogUtil.showProgress(activity,"文件上传中");
        HttpMethod.uploadFile(relationtype,list, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final UploadFile uploadFile= (UploadFile) object;
                if(uploadFile.isSussess()){

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            face.uploadSuccess(uploadFile.getData());
                        }
                    });

                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(uploadFile.getDesc());
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

        void uploadSuccess(String[] imgs);
    }
}
