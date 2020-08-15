package com.ylean.yb.student.fragment.user;

import android.view.View;
import android.widget.ListView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.news.MoneyAdapter;
import com.ylean.yb.student.adapter.user.news.SurveyAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import butterknife.BindView;

/**
 * Created by Administrator on 2020/3/29.
 */
public class SurveyFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页数
    private int page = 1;
    private SurveyAdapter adapter;

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
        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter=new SurveyAdapter(activity));
    }


    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
    }
}
