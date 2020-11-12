package com.ylean.yb.student.activity.user.bank;

import android.content.Intent;
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
import com.zxdc.utils.library.bean.ReceivablesheadBean;
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

        //获取收款信息对象
        collMoney= (CollMoneyBean.CollMoney) getIntent().getSerializableExtra("collMoney");

        //财务记录明细信息头部
        moneyIssueP.getReceivableshead(collMoney.getId());

        //获取资金发放明细
        moneyIssueP.getIssueRecord(collMoney.getBid());
    }


    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 财务记录明细信息头部
     * @param headBean
     */
    @Override
    public void getReceivableshead(ReceivablesheadBean.HeadBean headBean) {
        //获取头部view
        View headView= LayoutInflater.from(this).inflate(R.layout.head_money_issue,null);
        listView.addHeaderView(headView);
        final TextView tvTitle=headView.findViewById(R.id.tv_title);
        final TextView tvTime=headView.findViewById(R.id.tv_time);
        final TextView tvTotalMoney=headView.findViewById(R.id.tv_total_money);
        final TextView tvSendMoney=headView.findViewById(R.id.tv_send_money);
        final TextView tvSendNum=headView.findViewById(R.id.tv_send_num);
        final TextView tvGetMoney=headView.findViewById(R.id.tv_get_money);
        final TextView tvGetNum=headView.findViewById(R.id.tv_get_num);
        final TextView tvReissueNum=headView.findViewById(R.id.tv_reissue_num);
        tvTitle.setText(headBean.getBname());
        tvTime.setText(headBean.getYears()+"年");
        tvTotalMoney.setText(headBean.getMoney()*collMoney.getYears()+"元");
        tvSendMoney.setText(headBean.getYmoney()+"元");
        tvSendNum.setText(headBean.getYcount()+"次");
        tvGetMoney.setText(headBean.getDmoney()+"元");
        tvGetNum.setText(headBean.getDcount()+"次");
        tvReissueNum.setText(headBean.getYcount()-headBean.getDcount()+"次");
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //获取资金发放明细
            moneyIssueP.getIssueRecord(collMoney.getBid());
        }
    }
}
