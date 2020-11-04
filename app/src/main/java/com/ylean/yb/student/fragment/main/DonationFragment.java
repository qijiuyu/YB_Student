package com.ylean.yb.student.fragment.main;

import android.view.View;
import android.widget.ListView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.JZAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.DonationP;
import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 捐赠项目
 */
public class DonationFragment extends BaseFragment implements MyRefreshLayoutListener,DonationP.Face {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //数据集合
    private List<DonationBean.ListBean> listAll=new ArrayList<>();

    private DonationP donationP;

    /**
     * 加载布局
     *
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
        donationP=new DonationP(activity,this);

        //初始化列表
        reList.setMyRefreshLayoutListener(this);
        reList.setIsLoadingMoreEnabled(false);

        //获取网站项目捐赠列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        //获取网站项目捐赠列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }


    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        donationP.getDonation();
    }


    /**
     * 加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        reList.loadMoreComplete();
    }


    /**
     * 获取网站项目捐赠列表
     * @param list
     */
    @Override
    public void getDonation(List<DonationBean.ListBean> list) {
        reList.refreshComplete();
        listAll.clear();
        listAll.addAll(list);
        listView.setDivider(null);
        listView.setAdapter(new JZAdapter(activity,listAll));
    }
}
