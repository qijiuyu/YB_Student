package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.ApplyBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class ApplyP {

    private Activity activity;
    private Face face;

    public ApplyP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }


    /**
     * 获取申请记录
     */
    public void getApplyList(int page){
        HttpMethod.getApplyList(page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ApplyBean applyBean= (ApplyBean) object;
                if(applyBean==null){
                    return;
                }
                if(applyBean.isSussess()){

                    face.getApplyList(applyBean.getData());

                }else{
                    ToastUtil.showLong(applyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getApplyList(List<ApplyBean.ListBean> list);
    }
}
