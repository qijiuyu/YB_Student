package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class SelectPositionP {

    private Activity activity;
    private Face face;

    public SelectPositionP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 根据组合条件查询职位信息
     */
    public void getResumePostion(String parameter){
        HttpMethod.getResumePostion(parameter, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ResumePostion resumePostion= (ResumePostion) object;
                if(resumePostion.isSussess()){

                    face.getResumePostion(resumePostion.getData());

                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(resumePostion.getDesc());
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
        void getResumePostion(List<ResumePostion.Position> list);
    }
}
