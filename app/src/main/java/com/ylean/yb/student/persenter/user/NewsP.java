package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class NewsP{

    private Activity activity;
    private Face face;

    public NewsP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获取消息列表
     */
    public void getNewsList(int ctype,int page){
        HttpMethod.getNewsList(ctype, page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsBean newsBean= (NewsBean) object;
                if(newsBean.isSussess()){

                    face.getNewsList(newsBean.getData());

                }else{
                    ToastUtil.showLong(newsBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public interface Face{

        void getNewsList(List<NewsBean.News> list);
    }
}
