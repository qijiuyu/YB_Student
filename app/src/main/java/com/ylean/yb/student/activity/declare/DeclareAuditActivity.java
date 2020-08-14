package com.ylean.yb.student.activity.declare;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.EconomicAdapter;
import com.ylean.yb.student.adapter.declare.ShowFamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.ClickTextView;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 批次审核页面
 */
public class DeclareAuditActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_audit1)
    ImageView imgAudit1;
    @BindView(R.id.img_audit2)
    ImageView imgAudit2;
    @BindView(R.id.img_audit3)
    ImageView imgAudit3;
    @BindView(R.id.tv_audit1)
    TextView tvAudit1;
    @BindView(R.id.tv_audit2)
    TextView tvAudit2;
    @BindView(R.id.tv_audit3)
    TextView tvAudit3;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_batchNo)
    TextView tvBatchNo;
    @BindView(R.id.tv_valid_time)
    TextView tvValidTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nationality)
    TextView tvNationality;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_certificate_type)
    TextView tvCertificateType;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_card_time)
    TextView tvCardTime;
    @BindView(R.id.tv_hk_address)
    TextView tvHkAddress;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.tv_high_school)
    TextView tvHighSchool;
    @BindView(R.id.tv_university)
    TextView tvUniversity;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.list_family)
    MeasureListView listFamily;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.list_economic)
    MeasureListView listEconomic;
    @BindView(R.id.img_zm)
    ImageView imgZm;
    @BindView(R.id.img_fm)
    ImageView imgFm;
    @BindView(R.id.img_hk1)
    ImageView imgHk1;
    @BindView(R.id.img_hk2)
    ImageView imgHk2;
    @BindView(R.id.img_notice)
    ImageView imgNotice;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.tv_submit)
    ClickTextView tvSubmit;
    private ShowFamilyAdapter showFamilyAdapter;
    private EconomicAdapter economicAdapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_declare_audit;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("审核记录");

        //家庭成员列表
        listFamily.setAdapter(showFamilyAdapter=new ShowFamilyAdapter(this));

        //经济情况列表
        listEconomic.setAdapter(economicAdapter=new EconomicAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            case R.id.tv_submit:
                 finish();
                break;
            default:
                break;
        }
    }
}
