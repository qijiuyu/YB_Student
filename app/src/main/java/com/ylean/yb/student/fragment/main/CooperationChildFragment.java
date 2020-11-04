package com.ylean.yb.student.fragment.main;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.MainJLAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.CultrueP;
import com.zxdc.utils.library.bean.NewsListBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * 交流合作的子fragment
 */
public class CooperationChildFragment extends BaseFragment implements MyRefreshLayoutListener,CultrueP.Face3 {

    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page=1;
    //分类id
    private int id;
    //列表适配器
    private MainJLAdapter adapter;
    //数据集合
    private List<NewsListBean.ListBean> listAll=new ArrayList<>();

    private CultrueP cultrueP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.recycleview;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        cultrueP=new CultrueP(activity);
        cultrueP.setFace3(this);

        //初始化列表
        reList.setMyRefreshLayoutListener(this);
        listView.setLayoutManager(new GridLayoutManager(activity, 2));
        listView.setAdapter(adapter=new MainJLAdapter(activity,listAll));

        //获取网站新闻列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //获取网站新闻列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }


    /**
     * 刷新
     * @param view
     */
    private Handler handler=new Handler();
    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cultrueP.getNewsList(id,203,page);
            }
        },200);
    }


    /**
     * 加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
       page++;
        cultrueP.getNewsList(id,203,page);
    }


    /**
     * 获取id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取网站新闻列表
     * @param list
     */
    @Override
    public void getNewsList(List<NewsListBean.ListBean> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }
    }

}
