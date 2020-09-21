package com.ylean.yb.student.persenter.user;

import android.app.Activity;
import com.zxdc.utils.library.bean.ActivityNum;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class UserP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public UserP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    public UserP(Activity activity,Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }

    /**
     * 获取学生基本信息
     */
    public void getbaseinfo(){
        HttpMethod.getbaseinfo(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final UserInfo userInfo= (UserInfo) object;
                if(userInfo==null){
                    return;
                }
                if(userInfo.isSussess()){

                    face.getbaseinfo(userInfo);

                }else{
                    ToastUtil.showLong(userInfo.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 修改个人信息
     */
    public void updateUserInfo(String ucphone,String address,String qq,String residenceaddress,String uctel,String wechat){
        DialogUtil.showProgress(activity,"更新中");
        HttpMethod.updateUserInfo(ucphone, address, qq, residenceaddress, uctel, wechat, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){

                    face2.updateSuccess();

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 我的社团活动数量
     */
    public void getOwnActivityNum(PageParam pageParam){
        HttpMethod.getOwnActivityNum(pageParam, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ActivityNum activityNum= (ActivityNum) object;
                if(activityNum==null){
                    return;
                }
                if(activityNum.isSussess()){

                    face.getOwnActivityNum(activityNum.getData());

                }else{
                    ToastUtil.showLong(activityNum.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getbaseinfo(UserInfo userInfo);

        void getOwnActivityNum(ActivityNum.NumBean numBean);
    }

    public interface Face2{
        void updateSuccess();
    }
}
