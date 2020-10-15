package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.SendCodeP;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证手机号
 */
public class ValidationMobileActivity extends BaseActivity implements SendCodeP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code_img)
    EditText etCodeImg;
    @BindView(R.id.img_code)
    ImageView imgCode;
    private ForgetPwd forgetPwd;
    //身份证号
    private String idnum;
    //计数器
    private Timer mTimer;
    private int time = 0;
    private Handler handler=new Handler();
    private RequestOptions requestOptions;

    private SendCodeP sendCodeP=new SendCodeP(this);

    /**
     * 加载布局
     *
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
        sendCodeP.setFace2(this);
        tvTitle.setText("手机号验证");

        forgetPwd = (ForgetPwd) getIntent().getSerializableExtra("forgetPwd");
        idnum = getIntent().getStringExtra("idnum");
        if (forgetPwd != null) {
            tvContent.setText(Html.fromHtml("您输入的当前账号的验证手机号是<font color=\"#FA4D4F\">" + forgetPwd.getData().getPhone() + "</font> ，请输入短信验证码验证信息，以便对登录密码进行修改"));
        }

        requestOptions = new RequestOptions();
        //禁用磁盘缓存
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);///不使用磁盘缓存
        requestOptions.skipMemoryCache(true); // 不使用内存缓存
        //显示图形验证码
        Glide.with(this).load(HttpConstant.IP+"api/user/ckh/codeimg").apply(requestOptions).into(imgCode);
    }


    @OnClick({R.id.lin_back, R.id.tv_email,R.id.img_code, R.id.tv_send_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        final String codeImg=etCodeImg.getText().toString().trim();
        final String code = etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //切换到邮箱验证
            case R.id.tv_email:
                intent.setClass(this, ValidationEmailActivity.class);
                intent.putExtra("idnum", idnum);
                intent.putExtra("forgetPwd", forgetPwd);
                startActivityForResult(intent, 1000);
                break;
            //刷新图形验证码
            case R.id.img_code:
                 Glide.with(this).load(HttpConstant.IP+"api/user/ckh/codeimg").apply(requestOptions).into(imgCode);
                 break;
            //获取短信验证码
            case R.id.tv_send_code:
                if(time>0){
                    return;
                }
                if (forgetPwd == null) {
                    return;
                }
                if(TextUtils.isEmpty(codeImg)){
                    ToastUtil.showLong("请输入图形验证码");
                    return;
                }
                sendCodeP.getSmsCode(codeImg,forgetPwd.getData().getPhone(),"3");
                break;
            case R.id.tv_submit:
                if(TextUtils.isEmpty(codeImg)){
                    ToastUtil.showLong("请输入图形验证码");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入短信验证码");
                    return;
                }
                intent.setClass(this, ValidationSuccessActivity.class);
                intent.putExtra("idnum", idnum);
                intent.putExtra("code", code);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 1000);
                break;
            default:
                break;
        }
    }


    /**
     * 成功获取短信验证码
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            setResult(1000, new Intent());
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
        removeHandler(handler);
    }

}
