package com.ylean.yb.student.activity.user.apply;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.ApplyDetailsP;
import com.zxdc.utils.library.bean.ReissueAuditBean;
import com.zxdc.utils.library.view.MeasureListView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 补发申请详情
 */
public class ReissueAuditActivity extends BaseActivity implements ApplyDetailsP.Face {
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
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    //申请记录id
    private int id;

    private ApplyDetailsP applyDetailsP=new ApplyDetailsP(this);

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
        applyDetailsP.setFace(this);
        tvTitle.setText("补发申请详情");

        id=getIntent().getIntExtra("id",0);
        //获取申请记录详情
        applyDetailsP.getReissueAudit(id);

//        listView.setAdapter(adapter=new ReissueAuditAdapter(this));
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


    /**
     * 获取申请记录详情
     * @param dataBean
     */
    @Override
    public void getReissueAudit(ReissueAuditBean.DataBean dataBean) {
        if(dataBean==null){
            return;
        }
        tvTotalMoney.setText("补发金额总计："+dataBean.getMoney()+"元");
        tvBankCode.setText("银行卡号："+dataBean.getBnum());
        tvSendTime.setText("发放时间："+dataBean.getFdate());
    }
}
