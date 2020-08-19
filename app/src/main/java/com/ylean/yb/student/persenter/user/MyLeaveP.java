package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class MyLeaveP {
    private Activity activity;
    private Face face;
    private Face2 face2;

    public MyLeaveP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public MyLeaveP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }

    /**
     * 获取消息列表
     */
    public void getMyLeave(int page){
        HttpMethod.getMyLeave(page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final LeaveBean leaveBean= (LeaveBean) object;
                if(leaveBean.isSussess()){

                    face.getMyLeave(leaveBean.getData());

                }else{
                    ToastUtil.showLong(leaveBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 回复留言
     */
    public void reply(int id,String content){
        DialogUtil.showProgress(activity,"回复中");
        HttpMethod.reply(id, content, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.reply();

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getMyLeave(List<LeaveBean.Leave> list);
    }

    public interface Face2{
        void reply();
    }
}
