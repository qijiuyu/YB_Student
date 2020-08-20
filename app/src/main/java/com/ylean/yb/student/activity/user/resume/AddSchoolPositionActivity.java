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
 * 添加校内职务
 */
public class AddSchoolPositionActivity extends BaseActivity {
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
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
        return R.layout.activity_add_school_position;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @OnClick({R.id.tv_startTime, R.id.tv_endTime, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_startTime:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        tvStartTime.setText(time);
                    }
                });
                break;
            case R.id.tv_endTime:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        final boolean isTrue=SelectTimeUtils.judgeTime(time,tvStartTime.getText().toString().trim());
                        if(isTrue){
                            tvEndTime.setText(time);
                        }else{
                            ToastUtil.showLong("结束时间不能小于开始时间");
                        }
                    }
                });
                break;
            case R.id.tv_add:
                final String startTime=tvStartTime.getText().toString().trim();
                final String endTime=tvEndTime.getText().toString().trim();
                final String name=etName.getText().toString().trim();
                final String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(startTime)){
                    ToastUtil.showLong("请选择开始时间");
                    return;
                }
                if(TextUtils.isEmpty(endTime)){
                    ToastUtil.showLong("请选择结束时间");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入职务名称");
                    return;
                }
                if(TextUtils.isEmpty(memo)){
                    ToastUtil.showLong("请输入职务描述");
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
