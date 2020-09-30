package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.SendCodeP;
import com.ylean.yb.student.persenter.init.RegisterP;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * 绑定邮箱
 */
public class BindingEmailActivity extends BaseActivity implements SendCodeP.Face, RegisterP.Face3 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code)
    EditText etCode;
    //计数器
    private Timer mTimer;
    private int time = 0;
    //用户对象
    private Register userInfo;
    private SendCodeP sendEmailP;
    private RegisterP registerP;

    /**
     * 加载布局
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
        userInfo= (Register) getIntent().getSerializableExtra("userInfo");
        sendEmailP=new SendCodeP(this);
        sendEmailP.setFace(this);
        registerP=new RegisterP(this,this);
    }


    @OnClick({R.id.lin_back, R.id.tv_send_code, R.id.tv_confirm})
    public void onViewClicked(View view) {
        final String email=etEmail.getText().toString().trim();
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //发送邮箱验证码
            case R.id.tv_send_code:
                 if(TextUtils.isEmpty(email)){
                     ToastUtil.showLong("请输入邮箱地址");
                     return;
                 }
                if(time>0){
                    return;
                }
                sendEmailP.sendbindemail("0",email);
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
                //学生注册第三步
                registerP.bindingEmail(code,email,userInfo.getData().getId());
                break;
            default:
                break;
        }
    }


    /**
     * 邮箱验证码发送成功
     */
    private Handler handler=new Handler();
    @Override
    public void sendbindemail() {
        time=60;
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time <= 0) {
                    handler.post(new Runnable() {
                        public void run() {
                            mTimer.cancel();
                            tvSendCode.setText("获取验证码");
                        }
                    });
                } else {
                    --time;
                    handler.post(new Runnable() {
                        public void run() {
                            tvSendCode.setText(time + "秒");
                        }
                    });
                }
            }
        }, 0, 1000);
    }


    /**
     * 注册成功
     */
    @Override
    public void onSuccess() {
        setResult(1000,new Intent());
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer!=null){
            mTimer.cancel();
            mTimer.purge();
            mTimer=null;
        }
        removeHandler(handler);
    }
}
