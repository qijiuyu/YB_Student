package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.TempleteBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class ApplyReissueP {

    private Activity activity;
    private Face face;

    public ApplyReissueP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取申请补发模板
     */
    public void getReissueTemplate(){
        DialogUtil.showProgress(activity,"下载中");
        HttpMethod.getReissueTemplate(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final TempleteBean templeteBean= (TempleteBean) object;
                if(templeteBean==null){
                    return;
                }
                if(templeteBean.isSussess()){

                    face.getReissueTemplate(templeteBean.getData());

                }else{
                    ToastUtil.showLong(templeteBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getReissueTemplate(String url);
    }
}
