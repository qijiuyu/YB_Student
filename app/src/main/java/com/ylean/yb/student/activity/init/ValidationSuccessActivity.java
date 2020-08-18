package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.init.ForgetPwdP;
import com.zxdc.utils.library.util.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置新的登录密码
 */
public class ValidationSuccessActivity extends BaseActivity implements ForgetPwdP.Face2 {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    private String idnum,code;
    /**
     * 0：手机
     * 1：邮箱
     */
    private int type;
    private ForgetPwdP forgetPwdP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_validation_success;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        forgetPwdP=new ForgetPwdP(this,this);
        tvTitle.setText("设置新登录密码");
        idnum=getIntent().getStringExtra("idnum");
        code=getIntent().getStringExtra("code");
        type=getIntent().getIntExtra("type",0);
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
                forgetPwdP.findpwd(idnum,code,pwd,pwd2,type);
                break;
            default:
                break;
        }
    }


    /**
     * 修改成功
     */
    @Override
    public void onSuccess() {
        setResult(1000,new Intent());
        finish();
    }
}
