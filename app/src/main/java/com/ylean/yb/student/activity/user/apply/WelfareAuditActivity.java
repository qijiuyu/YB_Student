package com.ylean.yb.student.activity.user.apply;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.apply.WelfareAuditImgAdapter;
import com.ylean.yb.student.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公益审核详情
 */
public class WelfareAuditActivity extends BaseActivity {
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
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_welfare_time)
    TextView tvWelfareTime;
    @BindView(R.id.listView)
    RecyclerView listView;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welfare_audit;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("申请记录");

        listView.setLayoutManager(new GridLayoutManager(this, 2));
        listView.setAdapter(new WelfareAuditImgAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.tv_audit1, R.id.tv_audit2, R.id.tv_audit3, R.id.tv_submit})
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
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }
}
