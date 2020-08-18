package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.bean.ForgetPwd;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证手机号
 */
public class ValidationMobileActivity extends BaseActivity {
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

    /**
     * 加载布局
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
        tvTitle.setText("手机号验证");
        forgetPwd= (ForgetPwd) getIntent().getSerializableExtra("forgetPwd");
        idnum=getIntent().getStringExtra("idnum");
        if(forgetPwd!=null){
            tvContent.setText(Html.fromHtml("您输入的当前账号的验证手机号是<font color=\"#FA4D4F\">"+forgetPwd.getData().getPhone()+"</font> ，请输入短信验证码验证信息，以便对登录密码进行修改"));
        }
    }


    @OnClick({R.id.lin_back, R.id.tv_email, R.id.tv_send_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_email:
                intent.setClass(this,ValidationEmailActivity.class);
                intent.putExtra("idnum",idnum);
                startActivityForResult(intent,1000);
                break;
            //获取短信验证码
            case R.id.tv_send_code:
                 if(forgetPwd==null){
                     return;
                 }
                break;
            case R.id.tv_submit:
                intent.setClass(this,ValidationSuccessActivity.class);
                intent.putExtra("idnum",idnum);
                intent.putExtra("code",code);
                intent.putExtra("type",0);
                startActivityForResult(intent,1000);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            setResult(1000,new Intent());
            finish();
        }
    }
}
