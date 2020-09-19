package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

/**
 * 字典P
 */
public class DictP {

    private Activity activity;
    private Face face;

    public DictP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 根据type获取字典集合
     */
    public void getDict(int type){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getDict(type, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final DictBean dictBean= (DictBean) object;
                if(dictBean==null){
                    return;
                }
                if(dictBean.isSussess()){

                    face.getDict(dictBean.getData());

                }else{
                    ToastUtil.showLong(dictBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{
        void getDict(List<DictBean.Dict> list);
    }
}
