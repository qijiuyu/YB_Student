package com.ylean.yb.student.activity.user.resume;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加证书
 */
public class AddCertificateActivity extends BaseActivity {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_memo)
    EditText etMemo;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_certificate;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.tv_time, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        tvTime.setText(time);
                    }
                });
                break;
            case R.id.tv_add:
                final String time=tvTime.getText().toString().trim();
                final String name=etName.getText().toString().trim();
                final String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择获得时间");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入证书名称");
                    return;
                }
                break;
            case R.id.rel:
                 finish();
                 break;
            default:
                break;
        }
    }
}
