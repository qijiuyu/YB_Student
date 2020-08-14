package com.ylean.yb.student.activity.init;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_card)
    EditText etCard;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("登录");
        tvRegister.setText(Html.fromHtml("还没有账号，立即<font color=\"#FA4D4F\">注册</font>"));
    }

    @OnClick({R.id.lin_back, R.id.tv_register, R.id.tv_forget, R.id.tv_send_code, R.id.tv_update_mobile, R.id.tv_login})
    public void onViewClicked(View view) {
        final String card=etCard.getText().toString().trim();
        final String pwd=etPwd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_register:
                 setClass(RegisterActivity.class);
                break;
            //忘记密码
            case R.id.tv_forget:
                setClass(ValidationActivity.class);
                break;
            //获取验证码
            case R.id.tv_send_code:
                 if(TextUtils.isEmpty(card)){
                     ToastUtil.showLong("请输入身份证号");
                     return;
                 }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入密码");
                    return;
                }
                break;
            case R.id.tv_update_mobile:
                break;
            case R.id.tv_login:
                if(TextUtils.isEmpty(card)){
                    ToastUtil.showLong("请输入身份证号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入密码");
                    return;
                }
                break;
            default:
                break;
        }
    }
}
