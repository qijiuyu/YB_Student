package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;

public class UserP {

    private Activity activity;
    private Face face;

    public UserP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获取学生基本信息
     */
    public void getbaseinfo(){
        HttpMethod.getbaseinfo(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getbaseinfo();
    }
}
