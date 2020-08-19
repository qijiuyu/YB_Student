package com.ylean.yb.student.fragment.user;

import android.view.View;
import android.widget.ListView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.news.AuditAdapter;
import com.ylean.yb.student.adapter.user.news.MoneyAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.user.NewsP;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2020/3/29.
 */
public class MoneyFragment extends BaseFragment implements MyRefreshLayoutListener , NewsP.Face  {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页数
    private int page = 1;
    //消息集合
    private List<NewsBean.News> listAll=new ArrayList<>();
    //列表适配器
    private MoneyAdapter adapter;
    private NewsP newsP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.listview;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        newsP=new NewsP(activity,this);

        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter=new MoneyAdapter(activity,listAll));

        //加载数据
        if(view!=null && isVisibleToUser && listAll.size()==0){
            reList.startRefresh();
        }
    }


    /**
     * 返回消息列表数据
     * @param list
     */
    @Override
    public void getNewsList(List<NewsBean.News> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }
    }


    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        listAll.clear();
        page=1;
        newsP.getNewsList(2,page);
    }


    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        newsP.getNewsList(2,page);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //加载数据
        if(view!=null && isVisibleToUser && listAll.size()==0){
            reList.startRefresh();
        }
    }
}
