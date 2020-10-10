package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.IssueRecordBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class MoneyIssueP {

    private Activity activity;
    private Face face;

    public MoneyIssueP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }


    /**
     * 资金发放明细
     */
    public void getIssueRecord(int did){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getIssueRecord(did, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final IssueRecordBean issueRecordBean= (IssueRecordBean) object;
                if(issueRecordBean==null){
                    return;
                }
                if(issueRecordBean.isSussess()){

                    face.getIssueRecord(issueRecordBean.getData());

                }else{
                    ToastUtil.showLong(issueRecordBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getIssueRecord(List<IssueRecordBean.ListBean> list);
    }
}
