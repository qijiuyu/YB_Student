package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class InSchoolP {

    private Activity activity;
    private Face face;

    public InSchoolP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获得在校情况列表
     */
    public void getInSchoolList(int page){
        HttpMethod.getInSchoolList(page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final InSchoolBean inSchoolBean= (InSchoolBean) object;
                if(inSchoolBean.isSussess()){

                    face.getInSchoolList(inSchoolBean.getData());

                }else{
                    ToastUtil.showLong(inSchoolBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{
        void getInSchoolList(List<InSchoolBean.InSchool> list);
    }

}
