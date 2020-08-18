package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

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
                final UserInfo userInfo= (UserInfo) object;
                if(userInfo.isSussess()){

                    face.getbaseinfo(userInfo);

                }else{
                    ToastUtil.showLong(userInfo.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getbaseinfo(UserInfo userInfo);
    }
}
