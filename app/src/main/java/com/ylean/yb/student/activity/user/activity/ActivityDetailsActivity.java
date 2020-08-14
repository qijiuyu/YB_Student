package com.ylean.yb.student.activity.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 活动详情
 */
public class ActivityDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_welfare_time)
    TextView tvWelfareTime;
    @BindView(R.id.listView)
    MeasureListView listView;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_activity_details;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("活动详情");
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_submit:
                 setClass(AddWelfareActivity.class);
                break;
            default:
                break;
        }
    }
}
