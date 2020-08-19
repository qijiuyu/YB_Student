package com.ylean.yb.student.activity.declare;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.EconomicAdapter;
import com.ylean.yb.student.adapter.user.mine.FamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.SelectCallBack;
import com.ylean.yb.student.persenter.EconomicP;
import com.ylean.yb.student.persenter.FamilyP;
import com.ylean.yb.student.persenter.user.UserP;
import com.ylean.yb.student.view.AddFamilyView;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 批次申报
 */
public class AddDeclareActivity extends BaseActivity implements UserP.Face, FamilyP.Face , EconomicP.Face {
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
    @BindView(R.id.list_economic)
    MeasureListView listEconomic;
    //家庭成员集合
    private List<FamilyBean.ListBean> familyList=new ArrayList<>();
    //资助经济集合
    private List<EconomicBean.Economic> economicList=new ArrayList<>();
    //批次申报详情对象
    private BatchDetails.Batch batch;
    //用户基本信息对象
    private UserInfo userInfo;

    private UserP userP=new UserP(this,this);
    private FamilyP familyP=new FamilyP(this,this);
    private EconomicP economicP=new EconomicP(this,this);

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
        batch= (BatchDetails.Batch) getIntent().getSerializableExtra("batch");
        if(batch!=null){
            tvBatchNo.setText(batch.getName());
            tvValidTime.setText(batch.getStarttime().split(" ")[0]+"-"+batch.getEndtime().split(" ")[0]);
        }

        //查询用户基本信息
        userP.getbaseinfo();

        //查询家庭成员数据
        familyP.getFamilyList();

        //获取所有资助批次经济情况
        economicP.getEconomicList();
    }


    @OnClick({R.id.lin_back, R.id.tv_add_family,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //添加家庭成员
            case R.id.tv_add_family:
                new AddFamilyView(this, null, new SelectCallBack() {
                    @Override
                    public void selectBack(Object object) {
                        //查询家庭成员数据
                        familyP.getFamilyList();
                    }
                }).show();
                break;
            case R.id.tv_submit:
                 setClass(ApplySuccessActivity.class);
                 break;
            default:
                break;
        }
    }


    /**
     * 获取用户基本信息
     * @param userInfo
     */
    @Override
    public void getbaseinfo(UserInfo userInfo) {
        this.userInfo=userInfo;
        tvName.setText(userInfo.getData().getName());
        tvSex.setText(userInfo.getData().getSex());
        tvNationality.setText(userInfo.getData().getNationality());
        tvBirthday.setText(userInfo.getData().getBirthday().split(" ")[0]);
        tvNational.setText(userInfo.getData().getNation());
        tvCard.setText(userInfo.getData().getIdnum());
        if(!TextUtils.isEmpty(userInfo.getData().getValiditystarttime()) && !TextUtils.isEmpty(userInfo.getData().getValidityendtime())){
            tvCardTime.setText(userInfo.getData().getValiditystarttime().split(" ")[0]+"-"+userInfo.getData().getValidityendtime().split(" ")[0]);
        }
        tvEmail.setText(userInfo.getData().getEmail());
        tvMobile.setText(userInfo.getData().getPhone());
        if(!TextUtils.isEmpty(userInfo.getData().getAddress())){
            final Address address= (Address) JsonUtil.stringToObject(userInfo.getData().getAddress(),Address.class);
            tvHkAddress.setText(address.getAddress());
        }
    }


    /**
     * 查询家庭成员集合
     * @param list
     */
    @Override
    public void getFamily(List<FamilyBean.ListBean> list) {
        this.familyList=list;
        listFamily.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listFamily.setAdapter(new FamilyAdapter(this,familyList,familyP));
    }


    /**
     * 删除家庭成员
     * @param listBean
     */
    @Override
    public void deleteSuccess(FamilyBean.ListBean listBean) {
        familyList.remove(listBean);
        listFamily.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listFamily.setAdapter(new FamilyAdapter(this,familyList,familyP));
    }


    /**
     * 获取所有资助批次经济情况
     * @param list
     */
    @Override
    public void getEconomicList(List<EconomicBean.Economic> list) {
        this.economicList=list;
        listEconomic.setAdapter(new EconomicAdapter(this,list));
    }
}
