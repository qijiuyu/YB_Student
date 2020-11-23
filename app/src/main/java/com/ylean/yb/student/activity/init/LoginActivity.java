package com.ylean.yb.student.activity.init;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.TabActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.init.LoginP;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**18396831092 123456
 * 登录
 */
public class LoginActivity extends BaseActivity implements LoginP.Face {

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

    private LoginP loginP=new LoginP(this,this);
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
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //注册
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
            //手机号变更
            case R.id.tv_update_mobile:
                break;
            //登录
            case R.id.tv_login:
                if(TextUtils.isEmpty(card)){
                    ToastUtil.showLong("请输入身份证号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入密码");
                    return;
                }
                loginP.login(card,pwd);
                break;
            default:
                break;
        }
    }


    /**
     * 登录成功
     */
    @Override
    public void onSuccess(BaseBean baseBean) {
        //存储token
        SPUtil.getInstance(this).addString(SPUtil.TOKEN,baseBean.getToken());
        setClass(TabActivity.class);
        finish();
    }
}
