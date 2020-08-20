package com.ylean.yb.student.activity.user.resume;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加技能特长
 */
public class AddSpecialtyActivity extends BaseActivity {
    @BindView(R.id.et_language)
    EditText etLanguage;
    @BindView(R.id.tv_master)
    TextView tvMaster;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_specialty;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.tv_master, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_master:
                break;
            case R.id.tv_add:
                break;
            case R.id.rel:
                finish();
                break;
            default:
                break;
        }
    }
}
