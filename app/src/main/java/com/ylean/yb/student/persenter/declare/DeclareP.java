package com.ylean.yb.student.persenter.declare;

import android.app.Activity;

import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class DeclareP {

    private Activity activity;
    private Face face;

    public DeclareP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 学生获取申报记录
     */
    public void getDeclareList(){
        HttpMethod.getDeclareList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final DeclareBean declareBean= (DeclareBean) object;
                if(declareBean.isSussess()){

                    face.getDeclareList(declareBean.getData());

                }else{
                    ToastUtil.showLong(declareBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 学生获取可申报批次
     */
    public void getBatch(){
        HttpMethod.getBatch(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BatchBean batchBean= (BatchBean) object;
                if(batchBean.isSussess()){

                    face.getBatch(batchBean.getData());

                }else{
                    ToastUtil.showLong(batchBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{

        void getDeclareList(List<DeclareBean.Declare> list);

        void getBatch(List<BatchBean.Batch> list);
    }
}
