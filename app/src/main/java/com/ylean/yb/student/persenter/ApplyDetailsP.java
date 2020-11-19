package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.ReissueAuditBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class ApplyDetailsP {

    private Activity activity;
    private Face face;

    public ApplyDetailsP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    /**
     * 获取申请记录详情（财务或项目补发用）
     */
    public void getReissueAudit(int id){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getReissueAudit(id, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ReissueAuditBean reissueAuditBean= (ReissueAuditBean) object;
                if(reissueAuditBean==null){
                    return;
                }
                if(reissueAuditBean.isSussess()){

                    face.getReissueAudit(reissueAuditBean.getData());

                }else{
                    ToastUtil.showLong(reissueAuditBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getReissueAudit(ReissueAuditBean.DataBean dataBean);
    }
}
