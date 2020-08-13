package com.ylean.yb.student.activity.user.setting;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateMobileActivity2 extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code)
    EditText etCode;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_mobile2;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("修改手机号");
    }


    @OnClick({R.id.lin_back, R.id.tv_send_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        final String mobile=etMobile.getText().toString().trim();
        final String code=etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_send_code:
                 if(TextUtils.isEmpty(mobile)){
                     ToastUtil.showLong("请输入手机号");
                     return;
                 }
                 if(mobile.length()!=11){
                     ToastUtil.showLong("请输入正确的手机号");
                     return;
                 }
                break;
            case R.id.tv_submit:
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(mobile.length()!=11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入验证码");
                    return;
                }
                break;
            default:
                break;
        }
    }
}
