package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.TempleteBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class ApplyRefundP {

    private Activity activity;
    private Face face;

    public ApplyRefundP(Activity activity, Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取申请退还奖学金模板
     */
    public void getReturnTemplate(){
        DialogUtil.showProgress(activity,"下载中");
        HttpMethod.getReturnTemplate(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final TempleteBean templeteBean= (TempleteBean) object;
                if(templeteBean==null){
                    return;
                }
                if(templeteBean.isSussess()){

                    face.getReturnTemplate(templeteBean.getData());

                }else{
                    ToastUtil.showLong(templeteBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 申请退还奖学金
     */
    public void applyreturn(int fid, List<FileBean> list, String remarks){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.applyreturn(fid, list, remarks, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){

                    face.applyreturn();

                }else{
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(baseBean.getDesc());
                        }
                    });
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getReturnTemplate(String url);

        void applyreturn();
    }
}
