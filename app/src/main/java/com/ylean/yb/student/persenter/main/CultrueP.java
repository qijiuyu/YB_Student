package com.ylean.yb.student.persenter.main;

import android.app.Activity;

import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.NewsDetailsBean;
import com.zxdc.utils.library.bean.NewsListBean;
import com.zxdc.utils.library.bean.NewsSingle;
import com.zxdc.utils.library.bean.NewsTitleBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.List;

public class CultrueP {

    private Activity activity;
    private Face face;
    private Face2 face2;
    private Face3 face3;
    private Face4 face4;

    public CultrueP(Activity activity){
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
     * 获取网站新闻标题列表
     * 网站导航类型 201 机构文化 202 捐赠项目 203 合作交流 204 燕宝人 205 基金会动态
     */
    public  void getNewsTitle(int ctype){
        HttpMethod.getNewsTitle(ctype, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsTitleBean newsTitleBean= (NewsTitleBean) object;
                if(newsTitleBean==null){
                    return;
                }
                if(newsTitleBean.isSussess()){

                    face.getNewsTitle(newsTitleBean.getData());

                }else{

                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取网站新闻详细
     */
    public void getNewsDetails(int id){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getNewsDetails(id, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsDetailsBean newsDetailsBean= (NewsDetailsBean) object;
                if(newsDetailsBean==null){
                    return;
                }
                if(newsDetailsBean.isSussess()){

                    face2.getNewsDetails(newsDetailsBean.getData());

                }else{
                    ToastUtil.showLong(newsDetailsBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取网站新闻列表
     */
    public void getNewsList(int cid,int ctype,int page){
        HttpMethod.getNewsList(cid, ctype, page, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsListBean newsListBean= (NewsListBean) object;
                if(newsListBean==null){
                    return;
                }
                if(newsListBean.isSussess()){

                    face3.getNewsList(newsListBean.getData());

                }else{
                    ToastUtil.showLong(newsListBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取网站新闻单页
     */
    public void getNewsSingle(int cid,int ctype){
        HttpMethod.getNewsSingle(cid, ctype, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final NewsSingle newsSingle= (NewsSingle) object;
                if(newsSingle==null){
                    return;
                }
                if(newsSingle.isSussess()){

                    face4.getNewsSingle(newsSingle.getData());

                }else{
                    ToastUtil.showLong(newsSingle.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{
        void getNewsTitle(List<NewsTitleBean.ListBean> list);
    }

    public interface Face2{
        void getNewsDetails(NewsDetailsBean.DetailsBean detailsBean);
    }

    public interface Face3{
        void getNewsList(List<NewsListBean.ListBean> list);
    }

    public interface Face4{
        void getNewsSingle(NewsSingle.Single single);
    }

}
