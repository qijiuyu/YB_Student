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


    /**
     * 财务补发
     */
    public void financialreissue(int fid, List<FileBean> list, String remarks){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.financialreissue(fid, list, remarks, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){

                    face.financialreissue();

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
        void getReissueTemplate(String url);

        void financialreissue();
    }
}
