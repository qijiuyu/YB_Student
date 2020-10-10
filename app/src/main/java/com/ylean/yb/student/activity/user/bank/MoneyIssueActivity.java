package com.ylean.yb.student.activity.user.bank;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.bank.MoneyIssueAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MoneyIssueP;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.bean.IssueRecordBean;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 资金发放明细
 */
public class MoneyIssueActivity extends BaseActivity implements MoneyIssueP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //头部view
    private View headView;
    //收款信息对象
    private CollMoneyBean.CollMoney collMoney;

    private MoneyIssueP moneyIssueP=new MoneyIssueP(this);


    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_money_issue;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        moneyIssueP.setFace(this);
        tvTitle.setText("资金发放明细");

        collMoney= (CollMoneyBean.CollMoney) getIntent().getSerializableExtra("collMoney");

        //展示头部view
        showHead();

        //获取资金发放明细
        moneyIssueP.getIssueRecord(collMoney.getBid());
    }


    /**
     * 展示头部view
     */
    private void showHead(){
        //获取头部view
        headView= LayoutInflater.from(this).inflate(R.layout.head_money_issue,null);
        listView.addHeaderView(headView);
        final TextView tvTitle=headView.findViewById(R.id.tv_title);
        final TextView tvTime=headView.findViewById(R.id.tv_time);
        final TextView tvTotalMoney=headView.findViewById(R.id.tv_total_money);
        final TextView tvSendMoney=headView.findViewById(R.id.tv_send_money);
        final TextView tvSendNum=headView.findViewById(R.id.tv_send_num);
        final TextView tvGetMoney=headView.findViewById(R.id.tv_get_money);
        final TextView tvGetNum=headView.findViewById(R.id.tv_get_num);
        final TextView tvReissueNum=headView.findViewById(R.id.tv_reissue_num);
        final TextView tvApplyReissue=headView.findViewById(R.id.tv_apply_reissue);
        final TextView tvApplyRefund=headView.findViewById(R.id.tv_apply_refund);
        tvTitle.setText(collMoney.getBname());
        tvTime.setText(collMoney.getYears()+"年");
        tvTotalMoney.setText(collMoney.getMoney()*collMoney.getYears()+"元");
        tvSendMoney.setText(collMoney.getYmoney()+"元");
        tvSendNum.setText(collMoney.getYcount()+"次");
        tvGetMoney.setText(collMoney.getDmoney()+"元");
        tvGetNum.setText(collMoney.getDcount()+"次");
        tvReissueNum.setText(collMoney.getYcount()-collMoney.getDcount()+"次");


        //补发申请
        tvApplyReissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClass(ApplyReissueActivity.class);
            }
        });
        //申请退还金额
        tvApplyRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClass(ApplyRefundActivity.class);
            }
        });
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取资金发放明细
     * @param list
     */
    @Override
    public void getIssueRecord(List<IssueRecordBean.ListBean> list) {
        listView.setDivider(null);
        listView.setAdapter(new MoneyIssueAdapter(this,list));
    }
}
