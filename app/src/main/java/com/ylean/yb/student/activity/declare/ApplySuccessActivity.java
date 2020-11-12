package com.ylean.yb.student.activity.declare;

import android.view.View;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.TabActivity;
import com.ylean.yb.student.activity.user.apply.BankAuditActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.enumer.ApplyEnum;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申报成功
 */
public class ApplySuccessActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_des)
    TextView tvDes;
    private ApplyEnum applyEnum;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_success;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("提交成功");
        applyEnum = (ApplyEnum) getIntent().getSerializableExtra("applyEnum");
        switch (applyEnum) {
            case 批次申报成功:
                tvName.setText("您批次申请已提交成功！");
                tvDes.setText("基金会会尽快进行审核，请关注审核进度情况");
                break;
            case 银行卡变更成功:
                 tvName.setText("您的银行卡变更申请已提交成功！");
                 tvDes.setText("基金会会尽快进行审核，请关注审核进度情况");
                 break;
            case 在校情况:
                tvName.setText("您的在校情况说明已提交成功！");
                tvDes.setText("基金会会尽快进行审核，请关注审核进度情况");
                 break;
            case 申请补发:
                tvName.setText("您的补发申请已提交成功！");
                tvDes.setText("基金会会尽快进行审核，请关注审核进度情况");
                 break;
            case 申请退还奖学金:
                tvName.setText("您的申请退还奖学金已提交成功！");
                tvDes.setText("基金会会尽快进行审核，请关注审核进度情况");
                 break;
            default:
                break;
        }
    }


    @OnClick({R.id.lin_back, R.id.tv_main, R.id.tv_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_main:
                setClass(TabActivity.class);
                break;
            case R.id.tv_record:
                switch (applyEnum) {
                    case 批次申报成功:
                         setClass(DeclareAuditActivity.class);
                         break;
                    case 银行卡变更成功:
                         setClass(BankAuditActivity.class);
                         break;
                    default:
                        break;
                }
                finish();
                break;
            default:
                break;
        }
    }
}
