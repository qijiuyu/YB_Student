package com.ylean.yb.student.activity.user.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.bank.MoneyIssueAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资金发放明细
 */
public class MoneyIssueActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //头部view
    private View headView;
    private MoneyIssueAdapter adapter;


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
        tvTitle.setText("资金发放明细");

        //获取头部view
        headView= LayoutInflater.from(this).inflate(R.layout.head_money_issue,null);
        listView.setDivider(null);
        listView.addHeaderView(headView);
        listView.setAdapter(adapter=new MoneyIssueAdapter(this));
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }
}
