package com.ylean.yb.student.activity.declare;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.EconomicAdapter;
import com.ylean.yb.student.adapter.user.mine.FamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.bean.AddFamily;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 批次申报
 */
public class AddDeclareActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.et_high_school)
    EditText etHighSchool;
    @BindView(R.id.et_university)
    EditText etUniversity;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.list_family)
    RecyclerView listFamily;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.list_economic)
    MeasureListView listEconomic;
    private FamilyAdapter addFamilyAdapter;
    private EconomicAdapter economicAdapter;
    //家庭成员集合
    private List<AddFamily> familyList=new ArrayList<>();

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_declare;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("批次审报");

        //家庭成员列表
//        listFamily.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        listFamily.setAdapter(addFamilyAdapter=new FamilyAdapter(this,familyList));

        //经济情况列表
        listEconomic.setAdapter(economicAdapter=new EconomicAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.tv_add_family,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //添加家庭成员
            case R.id.tv_add_family:
                familyList.add(new AddFamily());
                addFamilyAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_submit:
                 setClass(ApplySuccessActivity.class);
                 break;
            default:
                break;
        }
    }
}
