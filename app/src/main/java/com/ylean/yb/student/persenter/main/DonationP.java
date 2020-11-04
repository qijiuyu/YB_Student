package com.ylean.yb.student.persenter.main;

import android.app.Activity;

import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.List;

public class DonationP {

    private Activity activity;
    private Face face;

    public DonationP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取网站项目捐赠列表
     */
    public void getDonation(){
        HttpMethod.getDonation(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final DonationBean donationBean= (DonationBean) object;
                if(donationBean==null){
                    return;
                }
                if(donationBean.isSussess()){

                    face.getDonation(donationBean.getData());

                }else{
                    ToastUtil.showLong(donationBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getDonation(List<DonationBean.ListBean> list);
    }
}
