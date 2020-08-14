package com.ylean.yb.student.activity.user.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.MyActivityAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的活动
 */
public class MyActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_activity_num)
    TextView tvActivityNum;
    @BindView(R.id.tv_no_apply)
    TextView tvNoApply;
    @BindView(R.id.tv_apply_success)
    TextView tvApplySuccess;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private MyActivityAdapter adapter;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_activity;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的活动");

        listView.setAdapter(adapter=new MyActivityAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(ActivityDetailsActivity.class);
            }
        });
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }
}
