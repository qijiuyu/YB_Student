package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;

public class MyBankP {

    private Activity activity;
    private Face face;

    public MyBankP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获取银行卡基本信息
     */
    public void getbankinfo(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getbankinfo(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{

    }
}
