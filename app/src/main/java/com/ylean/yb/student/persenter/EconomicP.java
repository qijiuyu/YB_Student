package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

/**
 * 批次经济
 */
public class EconomicP {

    private Activity activity;
    private Face face;

    public EconomicP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获取所有资助批次经济情况
     */
    public void getEconomicList(){
        HttpMethod.getEconomicList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final EconomicBean economicBean= (EconomicBean) object;
                if(economicBean==null){
                    return;
                }
                if(economicBean.isSussess()){

                    face.getEconomicList(economicBean.getData());

                }else{
                    ToastUtil.showLong(economicBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getEconomicList(List<EconomicBean.Economic> list);
    }

}
