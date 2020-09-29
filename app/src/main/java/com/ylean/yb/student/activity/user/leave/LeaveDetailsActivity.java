package com.ylean.yb.student.activity.user.leave;

import android.view.View;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.leave.ReplyLeaveAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.ReplyCallBack;
import com.ylean.yb.student.persenter.user.MyLeaveP;
import com.ylean.yb.student.view.ReplyView;
import com.zxdc.utils.library.bean.LeaveDetailsBean;
import com.zxdc.utils.library.view.MeasureListView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 留言详情
 */
public class LeaveDetailsActivity extends BaseActivity implements MyLeaveP.Face3 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.listView)
    MeasureListView listView;
    //留言id
    private int id;

    private MyLeaveP myLeaveP=new MyLeaveP(this,this);

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

        //获取留言详细
        id=getIntent().getIntExtra("id",0);
        myLeaveP.getLeaveDetails(id);
    }


    @OnClick({R.id.lin_back, R.id.tv_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_reply:
                new ReplyView(this,id,replyCallBack).show();
                break;
            default:
                break;
        }
    }


    /**
     * 获取详情数据
     * @param dataBean
     */
    @Override
    public void getLeaveDetails(LeaveDetailsBean.DataBean dataBean) {
        if(dataBean==null){
            return;
        }
        tvName.setText(dataBean.getMessage());
        tvTime.setText(dataBean.getCreatetime());
        listView.setAdapter(new ReplyLeaveAdapter(this,dataBean.getList()));
    }


    private ReplyCallBack replyCallBack=new ReplyCallBack() {
        @Override
        public void replySuccess() {
            myLeaveP.getLeaveDetails(id);
        }
    };


}
