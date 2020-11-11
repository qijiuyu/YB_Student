package com.ylean.yb.student.activity.user.setting;

import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.SendCodeP;
import com.ylean.yb.student.persenter.user.SettingP;
import com.ylean.yb.student.utils.CodeUtils;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 变更手机号
 */
public class UpdatePhoneActivity extends BaseActivity  implements SendCodeP.Face, SendCodeP.Face2 , SettingP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_email_code)
    EditText etEmailCode;
    @BindView(R.id.tv_send_email)
    TextView tvSendEmail;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_code_img)
    EditText etCodeImg;
    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.et_code_mobile)
    EditText etCodeMobile;
    @BindView(R.id.tv_send_mobile)
    TextView tvSendMobile;
    //计数器
    private Timer mTimer1,mTimer2;
    private int time1 = 0,time2 = 0;
    private Handler handler=new Handler();

    private UserInfo userInfo;

    private SendCodeP sendCodeP=new SendCodeP(this);
    private SettingP settingP=new SettingP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        sendCodeP.setFace(this);
        sendCodeP.setFace2(this);
        settingP.setFace2(this);
        tvTitle.setText("手机号变更");


        userInfo= (UserInfo) SPUtil.getInstance(this).getObject(SPUtil.USER_BASE_INFO,UserInfo.class);
        tvContent.setText(Html.fromHtml("您输入的当前账号的验证邮箱是<font color=\"#FA4D4F\">" + userInfo.getData().getEmail() + "</font> ， 请输入邮箱验证码进行手机号更换。"));

        //获取验证码图片
        imgCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }


    @OnClick({R.id.lin_back, R.id.tv_send_email, R.id.img_code, R.id.tv_send_mobile, R.id.tv_submit})
    public void onViewClicked(View view) {
        final String emailCode=etEmailCode.getText().toString().trim();
        final String mobile=etMobile.getText().toString().trim();
        final String codeImg=etCodeImg.getText().toString().trim();
        final String mobileCode=etCodeMobile.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //获取邮箱验证码
            case R.id.tv_send_email:
                if(time1>0){
                    return;
                }
                sendCodeP.sendbindemail("2",userInfo.getData().getEmail());
                break;
            //刷新图形验证码
            case R.id.img_code:
                imgCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
            //获取短信验证码
            case R.id.tv_send_mobile:
                if(time2>0){
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入新的手机号码");
                    return;
                }
                if(mobile.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号码");
                    return;
                }
                if(TextUtils.isEmpty(codeImg)){
                    ToastUtil.showLong("请输入图形验证码");
                    return;
                }
                if(!CodeUtils.getInstance().getCode().equals(codeImg)){
                    ToastUtil.showLong("图形验证码错误");
                    return;
                }
                sendCodeP.getSmsCode(codeImg,mobile,"2");
                break;
            case R.id.tv_submit:
                if(TextUtils.isEmpty(emailCode)){
                    ToastUtil.showLong("请输入邮箱验证码");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入新的手机号码");
                    return;
                }
                if(mobile.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号码");
                    return;
                }
                if(TextUtils.isEmpty(codeImg)){
                    ToastUtil.showLong("请输入图形验证码");
                    return;
                }
                if(!CodeUtils.getInstance().getCode().equals(codeImg)){
                    ToastUtil.showLong("图形验证码错误");
                    return;
                }
                if(TextUtils.isEmpty(mobileCode)){
                    ToastUtil.showLong("请输入短信验证码");
                    return;
                }
                settingP.updatePhone(mobileCode,emailCode,mobile);
                break;
            default:
                break;
        }
    }


    /**
     * 获取短信验证码
     */
    @Override
    public void getSmsCode() {
        time2=60;
        mTimer2 = new Timer();
        mTimer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time2 <= 0) {
                    handler.post(new Runnable() {
                        public void run() {
                            mTimer2.cancel();
                            tvSendMobile.setText("获取验证码");
                        }
                    });
                } else {
                    --time2;
                    handler.post(new Runnable() {
                        public void run() {
                            tvSendMobile.setText(time2 + "秒");
                        }
                    });
                }
            }
        }, 0, 1000);
    }


    /**
     * 获取邮箱验证码
     */
    @Override
    public void sendbindemail() {
        time1=60;
        mTimer1 = new Timer();
        mTimer1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time1 <= 0) {
                    handler.post(new Runnable() {
                        public void run() {
                            mTimer1.cancel();
                            tvSendEmail.setText("获取验证码");
                        }
                    });
                } else {
                    --time1;
                    handler.post(new Runnable() {
                        public void run() {
                            tvSendEmail.setText(time1 + "秒");
                        }
                    });
                }
            }
        }, 0, 1000);
    }


    /**
     * 变更成功
     */
    @Override
    public void updatePhone() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer1 != null) {
            mTimer1.cancel();
            mTimer1.purge();
            mTimer1 = null;
        }
        if (mTimer2 != null) {
            mTimer2.cancel();
            mTimer2.purge();
            mTimer2 = null;
        }
        removeHandler(handler);
    }

}
