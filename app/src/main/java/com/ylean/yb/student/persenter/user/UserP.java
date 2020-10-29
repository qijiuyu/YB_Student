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
    private Face3 face3;
    private Face4 face4;

    public UserP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
    }

    public void setFace3(Face3 face3){
        this.face3=face3;
    }

    public void setFace4(Face4 face4){
        this.face4=face4;
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
    public void updateUserInfo(String ucphone,String address,String qq,String uctel,String wechat){
        DialogUtil.showProgress(activity,"更新中");
        HttpMethod.updateUserInfo(ucphone, address, qq, uctel, wechat, new NetCallBack() {
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

                    face4.getOwnActivityNum(activityNum.getData());

                }else{
                    ToastUtil.showLong(activityNum.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取学生申报或查看申报基本信息
     */
    public void getUserInfoByApply(){
        DialogUtil.showProgress(activity,"加载中");
        HttpMethod.getUserInfoByApply(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final UserInfo userInfo= (UserInfo) object;
                if(userInfo==null){
                    return;
                }
                if(userInfo.isSussess()){

                    face3.getUserInfoByApply(userInfo.getData());

                }else{
                    ToastUtil.showLong(userInfo.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getbaseinfo(UserInfo userInfo);
    }

    public interface Face2{
        void updateSuccess();
    }

    public interface Face3{
        void getUserInfoByApply(UserInfo.UserBean userBean);
    }

    public interface Face4{
        void getOwnActivityNum(ActivityNum.NumBean numBean);
    }
}
