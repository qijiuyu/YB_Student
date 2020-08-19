package com.ylean.yb.student.activity.user.leave;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.leave.ReplyLeaveAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.view.ReplyView;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 留言详情
 */
public class LeaveDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.listView)
    MeasureListView listView;
    private LeaveBean.Leave leave;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_leave_details;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("留言详情");
        leave = (LeaveBean.Leave) getIntent().getSerializableExtra("leave");
        if(leave!=null){
            tvContent.setText(leave.getMessage());
            tvTime.setText(leave.getCreatetime());

            //显示回复列表
            listView.setAdapter(new ReplyLeaveAdapter(this,leave.getList()));
        }
    }


    @OnClick({R.id.lin_back, R.id.tv_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_reply:
                new ReplyView(this,leave.getId()).show();
                break;
            default:
                break;
        }
    }
}
