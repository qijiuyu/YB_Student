package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

public class AddFamilyP {

    private Activity activity;
    private Face face;

    public AddFamilyP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 添加家庭成员
     */
    public void addFamily(String familymembers){
        DialogUtil.showProgress(activity,"添加中");
        HttpMethod.addFamily(familymembers, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.addFamily();

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
        void addFamily();
    }
}
