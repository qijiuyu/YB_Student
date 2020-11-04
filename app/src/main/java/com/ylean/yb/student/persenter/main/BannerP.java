package com.ylean.yb.student.persenter.main;

import android.app.Activity;
import com.zxdc.utils.library.bean.BannerBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.List;

public class BannerP {

    private Activity activity;
    private Face face;

    public BannerP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取Banner列表信息
     * 所在页面 0 首页banner 1 交流合作banner 2 爱心社banner 可按顺序增加
     */
    public void getBanner(int pageindex){
        HttpMethod.getBanner(pageindex, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BannerBean bannerBean= (BannerBean) object;
                if(bannerBean==null){
                    return;
                }
                if(bannerBean.isSussess()){
                    face.getBanner(bannerBean.getData());
                }else{
                    ToastUtil.showLong(bannerBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getBanner(List<BannerBean.ListBean> list);
    }
}
