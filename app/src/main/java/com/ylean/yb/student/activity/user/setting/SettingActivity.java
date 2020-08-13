package com.ylean.yb.student.activity.user.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.et_nickName)
    EditText etNickName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_email)
    EditText etEmail;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("设置");
        tvRight.setText("完成");
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.img_head, R.id.tv_mobile, R.id.rel_pwd, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.img_head:
                break;
            case R.id.tv_mobile:
                setClass(UpdateMobileActivity1.class);
                break;
            case R.id.rel_pwd:
                setClass(UpdatePwdActivity1.class);
                break;
            case R.id.tv_login_out:
                break;
            default:
                break;
        }
    }
}
