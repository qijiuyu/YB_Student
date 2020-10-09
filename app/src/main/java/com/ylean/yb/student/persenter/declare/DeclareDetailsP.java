package com.ylean.yb.student.persenter.declare;

import android.app.Activity;

import com.zxdc.utils.library.bean.DeclareDetailsBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class DeclareDetailsP {

    private Activity activity;
    private Face face;

    public DeclareDetailsP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }


    /**
     * 获取申报基本信息
     */
    public  void getDeclareDetails(int did){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDeclareDetails(did, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final DeclareDetailsBean declareDetailsBean= (DeclareDetailsBean) object;
                if(declareDetailsBean==null){
                    return;
                }
                if(declareDetailsBean.isSussess()){

                    face.getDeclareDetails(declareDetailsBean.getData());

                }else{
                    ToastUtil.showLong(declareDetailsBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getDeclareDetails(DeclareDetailsBean.DetailsBean detailsBean);
    }
}
