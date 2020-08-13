package com.ylean.yb.student.activity.user.setting;

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

public class UpdatePwdActivity2 extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd2;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("修改登录密码");
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_submit:
                final String pwd=etPwd.getText().toString().trim();
                final String pwd2=etPwd2.getText().toString().trim();
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入新密码");
                    return;
                }
                if(TextUtils.isEmpty(pwd2)){
                    ToastUtil.showLong("请再次输入新密码");
                    return;
                }
                if(!pwd.equals(pwd2)){
                    ToastUtil.showLong("两次输入的密码不一致");
                    return;
                }
                break;
            default:
                break;
        }
    }
}
