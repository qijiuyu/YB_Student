package com.ylean.yb.student.activity.init;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定邮箱
 */
public class BindingEmailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code)
    EditText etCode;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding_email;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("邮箱绑定");
    }


    @OnClick({R.id.lin_back, R.id.tv_send_code, R.id.tv_confirm})
    public void onViewClicked(View view) {
        final String email=etEmail.getText().toString().trim();
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_send_code:
                 if(TextUtils.isEmpty(email)){
                     ToastUtil.showLong("请输入邮箱地址");
                     return;
                 }
                break;
            case R.id.tv_confirm:
                if(TextUtils.isEmpty(email)){
                    ToastUtil.showLong("请输入邮箱地址");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入邮箱验证码");
                    return;
                }
                break;
            default:
                break;
        }
    }
}
