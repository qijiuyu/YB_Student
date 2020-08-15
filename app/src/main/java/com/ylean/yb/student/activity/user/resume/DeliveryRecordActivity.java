package com.ylean.yb.student.activity.user.resume;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.DeliveryRecordAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 投递记录
 */
public class DeliveryRecordActivity extends BaseActivity implements MyRefreshLayoutListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private DeliveryRecordAdapter adapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_record;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("投递记录");

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(adapter=new DeliveryRecordAdapter(this));
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
