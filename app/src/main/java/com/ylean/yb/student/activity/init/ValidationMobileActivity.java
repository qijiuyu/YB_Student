package com.ylean.yb.student.activity.init;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.setting.UpdateMobileActivity2;
import com.ylean.yb.student.activity.user.setting.UpdatePwdActivity2;
import com.ylean.yb.student.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 验证手机号
 */
public class ValidationMobileActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_validation_mobile;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("手机号验证");
        tvContent.setText(Html.fromHtml("您输入的当前账号的验证手机号是<font color=\"#FA4D4F\">15022111134</font> ，请输入短信验证码验证信息，以便对登录密码进行修改"));
    }


    @OnClick({R.id.lin_back, R.id.tv_email, R.id.tv_send_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_email:
                setClass(ValidationEmailActivity.class);
                break;
            case R.id.tv_send_code:
                break;
            case R.id.tv_submit:
                setClass(UpdatePwdActivity2.class);
                break;
            default:
                break;
        }
    }
}
