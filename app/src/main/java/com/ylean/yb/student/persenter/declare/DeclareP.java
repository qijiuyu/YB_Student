package com.ylean.yb.student.persenter.declare;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class DeclareP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public DeclareP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public DeclareP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
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



    /**
     * 学生获取可申报批次详情
     */
    public void getBatchDetailed(int bid){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getBatchDetailed(bid, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BatchDetails batchDetails= (BatchDetails) object;
                if(batchDetails.isSussess()){

                    face2.getBatchDetailed(batchDetails.getData());

                }else{
                    ToastUtil.showLong(batchDetails.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 判断学生是否可以申报（学号）
     */
    public void checkdeclareno(int bid,String num){
        DialogUtil.showProgress(activity,"数据验证中");
        HttpMethod.checkdeclareno(bid, num, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.checkdeclareno();

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

        void getDeclareList(List<DeclareBean.Declare> list);

        void getBatch(List<BatchBean.Batch> list);
    }

    public interface Face2{
        void getBatchDetailed(BatchDetails.Batch batch);

        void checkdeclareno();
    }
}
