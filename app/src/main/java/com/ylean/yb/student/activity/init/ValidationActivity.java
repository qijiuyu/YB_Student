package com.ylean.yb.student.activity.init;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证身份证
 */
public class ValidationActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_card)
    EditText etCard;

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
                setClass(ValidationMobileActivity.class);
                break;
            default:
                break;
        }
    }
}
