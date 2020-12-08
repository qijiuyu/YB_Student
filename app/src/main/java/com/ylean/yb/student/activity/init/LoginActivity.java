package com.ylean.yb.student.activity.init;

import android.graphics.Paint;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.TabActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.SendCodeP;
import com.ylean.yb.student.persenter.init.LoginP;
import com.ylean.yb.student.utils.CodeUtils;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**18396831092 123456
 * 登录
 */
public class LoginActivity extends BaseActivity implements LoginP.Face, SendCodeP.Face2  {

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
    @BindView(R.id.lin_1)
    LinearLayout lin1;
    @BindView(R.id.lin_2)
    LinearLayout lin2;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_login_type)
    TextView tvLoginType;
    @BindView(R.id.et_code_img)
    EditText etCodeImg;
    @BindView(R.id.img_code)
    ImageView imgCode;

    //计数器
    private Timer mTimer;
    private int time = 0;
    private Handler handler=new Handler();

    private LoginP loginP=new LoginP(this,this);
    private SendCodeP sendCodeP=new SendCodeP(this);

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
        sendCodeP.setFace2(this);
        tvRegister.setText(Html.fromHtml("还没有账号，立即<font color=\"#FA4D4F\">注册</font>"));

        //设置下划线
        tvLoginType.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );

        //获取验证码图片
        imgCode.setImageBitmap(CodeUtils.getInstance().createBitmap());

    }

    @OnClick({R.id.lin_back, R.id.tv_register, R.id.tv_forget,R.id.tv_login_type,R.id.img_code, R.id.tv_send_code, R.id.tv_login})
    public void onViewClicked(View view) {
        final String card=etCard.getText().toString().trim();
        final String pwd=etPwd.getText().toString().trim();
        final String mobile=etMobile.getText().toString().trim();
        final String strImgCode=etCodeImg.getText().toString().trim();
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
            //切换登录方式
            case R.id.tv_login_type:
                 if(tvLoginType.getTag().equals("0")){
                     tvLoginType.setTag("1");
                     tvLoginType.setText("身份证号登录");
                     lin1.setVisibility(View.GONE);
                     lin2.setVisibility(View.VISIBLE);
                 }else{
                     tvLoginType.setTag("0");
                     tvLoginType.setText("手机验证");
                     lin1.setVisibility(View.VISIBLE);
                     lin2.setVisibility(View.GONE);
                 }
                 break;
            //刷新图形验证码
            case R.id.img_code:
                imgCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
            //获取验证码
            case R.id.tv_send_code:
                if(time>0){
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(mobile.length()<11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(strImgCode)){
                    ToastUtil.showLong("请输入图形验证码");
                    return;
                }
                if(!CodeUtils.getInstance().getCode().equals(strImgCode)){
                    ToastUtil.showLong("图形验证码错误");
                    return;
                }
                sendCodeP.getSmsCode(strImgCode,mobile,"1");
                break;
            //登录
            case R.id.tv_login:
                if(tvLoginType.getTag().equals("0")){
                    if(TextUtils.isEmpty(card)){
                        ToastUtil.showLong("请输入身份证号");
                        return;
                    }
                    if(TextUtils.isEmpty(pwd)){
                        ToastUtil.showLong("请输入密码");
                        return;
                    }
                    loginP.login(card,pwd);
                }else{
                    if(TextUtils.isEmpty(mobile)){
                        ToastUtil.showLong("请输入手机号");
                        return;
                    }
                    if(mobile.length()<11){
                        ToastUtil.showLong("请输入正确的手机号");
                        return;
                    }
                    if(TextUtils.isEmpty(strImgCode)){
                        ToastUtil.showLong("请输入图形验证码");
                        return;
                    }
                    if(TextUtils.isEmpty(code)){
                        ToastUtil.showLong("请输入短信验证码");
                        return;
                    }
                    loginP.loginByCode(code,mobile);
                }
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
     * 登录成功
     */
    @Override
    public void onSuccess(BaseBean baseBean) {
        //存储token
        SPUtil.getInstance(this).addString(SPUtil.TOKEN,baseBean.getToken());
        //是否处于登录状态
        SPUtil.getInstance(activity).addBoolean(SPUtil.IS_LOGIN,true);
        setClass(TabActivity.class);
        finish();
    }
}
