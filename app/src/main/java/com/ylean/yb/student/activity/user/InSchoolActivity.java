package com.ylean.yb.student.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.InSchoolAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在校情况
 */
public class InSchoolActivity extends BaseActivity implements MyRefreshLayoutListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private InSchoolAdapter adapter;
    //页数
    private int page = 1;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_in_school;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("在校情况");

        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter=new InSchoolAdapter(this));
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
