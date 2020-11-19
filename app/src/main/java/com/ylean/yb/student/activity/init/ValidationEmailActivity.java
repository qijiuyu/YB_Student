package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.SendCodeP;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证邮箱
 */
public class ValidationEmailActivity extends BaseActivity implements SendCodeP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    private ForgetPwd forgetPwd;
    //身份证号
    private String idnum;
    //计数器
    private Timer mTimer;
    private int time = 0;

    private SendCodeP sendEmailP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_validation_email;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        sendEmailP=new SendCodeP(this);
        sendEmailP.setFace(this);

        tvTitle.setText("邮箱验证");
        forgetPwd= (ForgetPwd) getIntent().getSerializableExtra("forgetPwd");
        idnum=getIntent().getStringExtra("idnum");
        if(forgetPwd!=null){
            tvContent.setText(Html.fromHtml("您输入的当前账号的验证邮箱是<font color=\"#FA4D4F\">"+forgetPwd.getData().getEmail()+"</font>  ，请输入邮箱验证码验证信息，以便对登录密码进行修改"));
        }
    }


    @OnClick({R.id.lin_back, R.id.tv_mobile, R.id.tv_send_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_mobile:
                finish();
                break;
            //获取邮箱验证码
            case R.id.tv_send_code:
                if(time>0){
                    return;
                }
                sendEmailP.sendbindemailByNum("1",idnum);
                break;
            //下一步
            case R.id.tv_submit:
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入邮箱验证码");
                    return;
                }
                Intent intent=new Intent(this,ValidationSuccessActivity.class);
                intent.putExtra("idnum",idnum);
                intent.putExtra("code",code);
                intent.putExtra("type",1);
                startActivityForResult(intent,1000);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            setResult(1000,new Intent());
            finish();
        }
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
