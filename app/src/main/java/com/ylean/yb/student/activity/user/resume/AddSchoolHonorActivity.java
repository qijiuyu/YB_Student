package com.ylean.yb.student.activity.user.resume;

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
import butterknife.OnClick;

/**
 * 添加在校荣誉
 */
public class AddSchoolHonorActivity extends BaseActivity {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_award)
    EditText etAward;
    @BindView(R.id.et_level)
    EditText etLevel;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_school_honor;
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
                final String award=etAward.getText().toString().trim();
                final String level=etLevel.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择时间");
                    return;
                }
                if(TextUtils.isEmpty(award)){
                    ToastUtil.showLong("请输入奖项");
                    return;
                }
                if(TextUtils.isEmpty(level)){
                    ToastUtil.showLong("请输入级别");
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
