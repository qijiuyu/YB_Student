package com.ylean.yb.student.activity.user.apply;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.ApplyRecordAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请记录
 */
public class ApplyRecordActivity extends BaseActivity implements MyRefreshLayoutListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private ApplyRecordAdapter adapter;
    //页数
    private int page = 1;


    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_record;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("申请记录");

        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter=new ApplyRecordAdapter(this));
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
