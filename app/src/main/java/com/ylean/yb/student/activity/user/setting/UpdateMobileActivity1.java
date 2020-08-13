package com.ylean.yb.student.activity.user.setting;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改手机号
 */
public class UpdateMobileActivity1 extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_mobile1;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("验证手机号");
    }

    @OnClick({R.id.lin_back, R.id.tv_send_code,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                 break;
            case R.id.tv_send_code:

                break;
            case R.id.tv_submit:
                 setClass(UpdateMobileActivity2.class);
                 break;
            default:
                break;
        }
    }
}
