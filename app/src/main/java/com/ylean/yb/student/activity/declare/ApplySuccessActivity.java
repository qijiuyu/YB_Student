package com.ylean.yb.student.activity.declare;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 申报成功
 */
public class ApplySuccessActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_success;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("提交成功");
    }


    @OnClick({R.id.lin_back, R.id.tv_main, R.id.tv_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_main:
                break;
            case R.id.tv_record:
                break;
            default:
                break;
        }
    }
}
