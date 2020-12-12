package com.ylean.yb.student.activity.user.bank;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.bank.HistoryBankAdapter;
import com.ylean.yb.student.adapter.user.bank.MyBankAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyBankP;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的银行卡
 */
public class MyBankActivity extends BaseActivity implements MyBankP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.lin_no)
    LinearLayout linNo;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.list_bank)
    MeasureListView listBank;
    @BindView(R.id.lin_nobank)
    LinearLayout linBank;
    /**
     * 银行卡基本信息与历史信息
     */
    private List<BankBaseBean.BankBase> bankList = new ArrayList<>();
    private HistoryBankAdapter adapter;
    private MyBankP myBankP = new MyBankP(this);

    /**
     * 加载布局
     *
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
        myBankP.setFace(this);
        tvTitle.setText("我的银行卡");

        adapter=new HistoryBankAdapter(this, bankList);
        listBank.setAdapter(adapter);

        //获取银行卡基本信息
        myBankP.getbankinfo();

        //获取银行卡历史信息
        myBankP.getBankHistory();

        //收款信息
        myBankP.getCollMoneyList();

    }


    @OnClick({R.id.lin_back, R.id.tv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //显示历史银行卡
            case R.id.tv_history:
                 if(bankList.size()==1){
                     return;
                 }
                 if(adapter.isShowHistory){
                     adapter.setIsShow(false);
                     tvHistory.setText("查看历史银行卡");
                 }else{
                     adapter.setIsShow(true);
                     tvHistory.setText("隐藏历史银行卡");
                 }
                break;
            default:
                break;
        }
    }


    /**
     * 获取银行卡基本信息
     *
     * @param bankBase
     */
    @Override
    public void getbankinfo(BankBaseBean.BankBase bankBase) {
        if (bankBase == null) {
            linBank.setVisibility(View.VISIBLE);
            return;
        }
        bankList.add(0, bankBase);
        adapter.notifyDataSetChanged();
    }


    /**
     * 获取历史银行卡
     * @param list
     */
    @Override
    public void getBankHistory(List<BankBaseBean.BankBase> list) {
        if (list == null) {
            return;
        }
        bankList.addAll(list);
        adapter.notifyDataSetChanged();
    }


    /**
     * 获取收款明细
     * @param list
     */
    @Override
    public void getCollMoneyList(final List<CollMoneyBean.CollMoney> list) {
        if (list == null || list.size() == 0) {
            linNo.setVisibility(View.VISIBLE);
            return;
        }
        listView.setAdapter(new MyBankAdapter(this, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,MoneyIssueActivity.class);
                intent.putExtra("collMoney",list.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //获取银行卡基本信息
            myBankP.getbankinfo();
        }
    }
}
