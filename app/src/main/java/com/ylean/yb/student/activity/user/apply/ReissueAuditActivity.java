package com.ylean.yb.student.activity.user.apply;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.apply.ReissueAuditAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 补发申请详情
 */
public class ReissueAuditActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_status)
    ImageView imgStatus;
    @BindView(R.id.tv_audit1)
    TextView tvAudit1;
    @BindView(R.id.tv_audit2)
    TextView tvAudit2;
    @BindView(R.id.tv_audit3)
    TextView tvAudit3;
    @BindView(R.id.listView)
    MeasureListView listView;
    private ReissueAuditAdapter adapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reissue_audit;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("补发申请详情");

        listView.setAdapter(adapter=new ReissueAuditAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.tv_audit1, R.id.tv_audit2, R.id.tv_audit3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_audit1:
                break;
            case R.id.tv_audit2:
                break;
            case R.id.tv_audit3:
                break;
            default:
                break;
        }
    }
}
