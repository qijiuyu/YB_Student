package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.init.ForgetPwdP;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证身份证
 */
public class ValidationActivity extends BaseActivity implements ForgetPwdP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_card)
    EditText etCard;
    //P层对象
    private ForgetPwdP forgetPwdP=new ForgetPwdP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_validation;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("账号验证");
    }

    @OnClick({R.id.lin_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_next:
                final String card=etCard.getText().toString().trim();
                if(TextUtils.isEmpty(card)){
                    ToastUtil.showLong("请输入身份证号码");
                    return;
                }
                if(card.length()<18){
                    ToastUtil.showLong("请输入正确的身份证号");
                    return;
                }
                forgetPwdP.findfirstpwd(card);
                break;
            default:
                break;
        }
    }


    /**
     * 验证成功
     */
    @Override
    public void onSuccess(ForgetPwd forgetPwd) {
        Intent intent=new Intent(this,ValidationMobileActivity.class);
        intent.putExtra("forgetPwd",forgetPwd);
        intent.putExtra("idnum",etCard.getText().toString().trim());
        startActivityForResult(intent,1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            finish();
        }
    }

}
