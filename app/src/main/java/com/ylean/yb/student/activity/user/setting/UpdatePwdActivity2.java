package com.ylean.yb.student.activity.user.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SettingP;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePwdActivity2 extends BaseActivity implements SettingP.Face3 {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;

    //旧密码
    private String oldPwd;

    private SettingP settingP=new SettingP(this);

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
        settingP.setFace3(this);
        tvTitle.setText("修改登录密码");
        oldPwd=getIntent().getStringExtra("oldPwd");
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
                settingP.updatePwd(pwd,pwd2,oldPwd);
                break;
            default:
                break;
        }
    }


    /**
     * 修改成功
     */
    @Override
    public void updatePwd() {
        setResult(1000,new Intent());
        finish();
    }
}
