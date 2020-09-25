package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.DeliveryBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.parameter.GetDeliveryRecord;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class DeliveryRecordP {

    private Activity activity;
    private Face face;

    public DeliveryRecordP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }


    /**
     * 查询投递记录
     */
    public void getDeliveryRecord(GetDeliveryRecord getDeliveryRecord){
        HttpMethod.getDeliveryRecord(getDeliveryRecord, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final DeliveryBean deliveryBean= (DeliveryBean) object;
                if(deliveryBean==null){
                    return;
                }
                if(deliveryBean.isSussess()){

                    face.getDeliveryRecord(deliveryBean.getData());

                }else{
                    ToastUtil.showLong(deliveryBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getDeliveryRecord(List<DeliveryBean.ListBean> list);
    }
}
