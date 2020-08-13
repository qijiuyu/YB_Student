package com.ylean.yb.student.activity.user.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePwdActivity1 extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd1;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("验证旧密码");
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_submit:
                setClass(UpdatePwdActivity2.class);
                break;
            default:
                break;
        }
    }
}
