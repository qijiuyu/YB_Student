package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.bean.parameter.Position;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class SelectPositionP {

    private Activity activity;
    private Face face;

    public SelectPositionP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 查询职位类型信息
     */
    public void getResumePostion(){
        HttpMethod.getResumePostion(new Position(), new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final ResumePostion resumePostion= (ResumePostion) object;
                if(resumePostion.isSussess()){

                    face.getResumePostion(resumePostion.getData());

                }else{
                    ToastUtil.showLong(resumePostion.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public interface Face{
        void getResumePostion(List<ResumePostion.Position> list);
    }
}
