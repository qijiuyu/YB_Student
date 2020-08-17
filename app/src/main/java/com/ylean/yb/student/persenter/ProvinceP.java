package com.ylean.yb.student.persenter;

import android.app.Activity;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class ProvinceP {

    private Activity activity;
    private Face face;

    public ProvinceP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取所有省集合
     */
    public void getProvince(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getProvince(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ProvinceBean provinceBean= (ProvinceBean) object;
                if(provinceBean.isSussess()){
                    face.getProvince(provinceBean.getData());
                }else{
                    ToastUtil.showLong(provinceBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 根据省代码获取市集合
     */
    public void getCityByProvince(String code){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getCityByProvince(code,new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ProvinceBean provinceBean= (ProvinceBean) object;
                if(provinceBean.isSussess()){
                    face.getCityByProvince(provinceBean.getData());
                }else{
                    ToastUtil.showLong(provinceBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 根据市代码获取区集合
     */
    public void getAreaByCity(String code){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getAreaByCity(code,new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ProvinceBean provinceBean= (ProvinceBean) object;
                if(provinceBean.isSussess()){
                    face.getAreaByCity(provinceBean.getData());
                }else{
                    ToastUtil.showLong(provinceBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{
        void getProvince(List<ProvinceBean.ListBean> list);

        void getCityByProvince(List<ProvinceBean.ListBean> list);

        void getAreaByCity(List<ProvinceBean.ListBean> list);
    }

}
