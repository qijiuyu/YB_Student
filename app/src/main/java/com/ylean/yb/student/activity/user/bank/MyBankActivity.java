package com.ylean.yb.student.activity.user.bank;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.bank.HistoryBankAdapter;
import com.ylean.yb.student.adapter.user.bank.MyBankAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyBankP;
import com.zxdc.utils.library.view.MeasureListView;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的银行卡
 */
public class MyBankActivity extends BaseActivity implements MyRefreshLayoutListener, MyBankP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private MeasureListView historyList;
    private MyBankAdapter myBankAdapter;
    /**
     * 头部view
     */
    private View headView;
    private MyBankP myBankP=new MyBankP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mybank;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的银行卡");

        myBankP.getbankinfo();

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(myBankAdapter=new MyBankAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(MoneyIssueActivity.class);
            }
        });


        //显示历史银行卡
        showHistory();
    }


    /**
     * 显示历史银行卡
     */
    private void showHistory(){
        //加载历史银行卡view
        headView= LayoutInflater.from(this).inflate(R.layout.view_bank_head,null);
        listView.addHeaderView(headView);
        historyList=headView.findViewById(R.id.listView);
        historyList.setAdapter(new HistoryBankAdapter(this));
    }




    @OnClick({R.id.lin_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }
}
