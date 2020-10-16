package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.AuditBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class AuditStatusP {

    private Activity activity;
    private Face face;

    public AuditStatusP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取审核信息
     */
    public void getAudit(int did){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getAudit(did, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final AuditBean auditBean= (AuditBean) object;
                if(auditBean==null){
                    return;
                }
                if(auditBean.isSussess()){

                    face.getAudit(auditBean.getData());

                }else{
                    ToastUtil.showLong(auditBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getAudit(AuditBean.Audit audit);
    }
}
