package com.ylean.yb.student.persenter.declare;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class ApplyDeclareP {

    private Activity activity;
    private Face face;

    public ApplyDeclareP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 学生申报批次
     */
    public void applyDeclare(int bid,String jkids,String idpositive,String idback,String householder,String oneself,String acceptanceletter,String relevantdoc){
        DialogUtil.showProgress(activity,"提交中");
        HttpMethod.applyDeclare(bid, jkids, idpositive, idback, householder, oneself, acceptanceletter, relevantdoc, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.applySuccess();

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
        void applySuccess();
    }
}
