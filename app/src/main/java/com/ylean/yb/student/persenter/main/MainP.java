package com.ylean.yb.student.persenter.main;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.NewsListBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class MainP {

    private Activity activity;
    private Face face;

    public MainP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }


    /**
     * 获取网站首页新闻列表
     */
    public void getMainNews(int ctype,int top){
        HttpMethod.getMainNews(ctype, top, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsListBean newsListBean= (NewsListBean) object;
                if(newsListBean==null){
                    return;
                }
                if(newsListBean.isSussess()){

                    face.getMainNews(newsListBean.getData());

                }else{
                    ToastUtil.showLong(newsListBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{
        void getMainNews(List<NewsListBean.ListBean> list);
    }
}
