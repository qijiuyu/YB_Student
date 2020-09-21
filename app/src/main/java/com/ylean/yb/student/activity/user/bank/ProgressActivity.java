package com.ylean.yb.student.activity.user.bank;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.bank.ProgressAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyBankP;
import com.zxdc.utils.library.bean.BankProgress;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 银行卡办理进度
 */
public class ProgressActivity extends BaseActivity implements MyBankP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.lin_no)
    LinearLayout linNo;

    private MyBankP myBankP=new MyBankP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_progress;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("银行卡办理进度");

        //获取银行卡办里进度
        myBankP.setFace2(this);
        myBankP.getBankProgress();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取进度数据
     * @param list
     */
    @Override
    public void getBankProgress(List<BankProgress.Progress> list) {
        if(list!=null && list.size()>0){
            linNo.setVisibility(View.GONE);
        }
        listView.setAdapter(new ProgressAdapter(this,list));
    }
}
