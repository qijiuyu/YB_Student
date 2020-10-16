package com.ylean.yb.student.activity.declare;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.AuditAdapter;
import com.ylean.yb.student.adapter.declare.ShowEconomicAdapter;
import com.ylean.yb.student.adapter.declare.ShowFamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.AuditStatusP;
import com.ylean.yb.student.persenter.FamilyP;
import com.ylean.yb.student.persenter.declare.DeclareDetailsP;
import com.ylean.yb.student.persenter.user.UserP;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.AuditBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.DeclareDetailsBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.view.ClickTextView;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 批次审核页面
 */
public class DeclareAuditActivity extends BaseActivity implements UserP.Face3,FamilyP.Face, DeclareDetailsP.Face, AuditStatusP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.list_audit)
    RecyclerView listAudit;
//    @BindView(R.id.img_audit1)
//    ImageView imgAudit1;
//    @BindView(R.id.img_audit2)
//    ImageView imgAudit2;
//    @BindView(R.id.img_audit3)
//    ImageView imgAudit3;
//    @BindView(R.id.tv_audit1)
//    TextView tvAudit1;
//    @BindView(R.id.tv_audit2)
//    TextView tvAudit2;
//    @BindView(R.id.tv_audit3)
//    TextView tvAudit3;
    @BindView(R.id.tv_batchNo)
    TextView tvBatchNo;
    @BindView(R.id.tv_code)
    TextView tvCode;
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

    private AuditStatusP auditStatusP=new AuditStatusP(this,this);
    private UserP userP=new UserP(this);
    private FamilyP familyP = new FamilyP(this, this);
    private DeclareDetailsP declareDetailsP=new DeclareDetailsP(this);

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
        final DeclareBean.Declare declare= (DeclareBean.Declare) getIntent().getSerializableExtra("declare");

        //获取审核信息
        auditStatusP.getAudit(declare.getGbid());

        //获取学生申报或查看申报基本信息
        userP.setFace3(this);
        userP.getUserInfoByApply();

        //查询家庭成员数据
        familyP.getFamilyList();

        //获取申报基本信息
        declareDetailsP.setFace(this);
        declareDetailsP.getDeclareDetails(declare.getBdid());
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_submit:
                 finish();
                break;
            default:
                break;
        }
    }


    /**
     * 获取审核信息
     */
    @Override
    public void getAudit(AuditBean.Audit audit) {
        tvCode.setText(audit.getCode());
        tvBatchNo.setText(audit.getBname());

        if(audit.getAtype()==0 || audit.getAtype()==1){
            listAudit.setLayoutManager(new GridLayoutManager(this, 3));
        }else{
            listAudit.setLayoutManager(new GridLayoutManager(this, 2));
        }
        listAudit.setAdapter(new AuditAdapter(this,audit));
    }


    /**
     * 显示用户基本信息
     */
    @Override
    public void getUserInfoByApply(UserInfo.UserBean userBean) {
        if(userBean==null){
            return;
        }
        if(!TextUtils.isEmpty(userBean.getPhoto())){
            Glide.with(this).load(userBean.getPhoto()).into(imgHead);
        }
        tvName.setText(userBean.getName());
        tvSex.setText(userBean.getSex());
        tvNationality.setText(userBean.getNationality());
        if(!TextUtils.isEmpty(userBean.getBirthday())){
            tvBirthday.setText(userBean.getBirthday().split(" ")[0]);
        }
        tvNational.setText(userBean.getNation());
        tvCard.setText(userBean.getIdnum());
        if (!TextUtils.isEmpty(userBean.getValiditystarttime()) && !TextUtils.isEmpty(userBean.getValidityendtime())) {
            tvCardTime.setText(userBean.getValiditystarttime().split(" ")[0] + "-" + userBean.getValidityendtime().split(" ")[0]);
        }
        tvEmail.setText(userBean.getEmail());
        tvMobile.setText(userBean.getPhone());
        if (!TextUtils.isEmpty(userBean.getResidenceaddress())) {
            final Address address = (Address) JsonUtil.stringToObject(userBean.getResidenceaddress(), Address.class);
            tvHkAddress.setText(address.getAddress());
        }
        scrollView.scrollTo(0,0);

    }


    /**
     * 查询家庭成员集合
     * @param list
     */
    @Override
    public void getFamily(List<FamilyBean.ListBean> list) {
        listFamily.setAdapter(new ShowFamilyAdapter(this,list));
        scrollView.scrollTo(0,0);
    }

    @Override
    public void deleteSuccess(FamilyBean.ListBean listBean) {
    }


    /**
     * 获取申报基本信息
     */
    @Override
    public void getDeclareDetails(DeclareDetailsBean.DetailsBean detailsBean) {
        if(detailsBean==null){
            return;
        }
        listEconomic.setAdapter(new ShowEconomicAdapter(this,detailsBean.getEconomic()));
        if(!TextUtils.isEmpty(detailsBean.getIdcardimgz())){
            Glide.with(this).load(detailsBean.getIdcardimgz()).into(imgZm);
        }
        if(!TextUtils.isEmpty(detailsBean.getIdcardimgf())){
            Glide.with(this).load(detailsBean.getIdcardimgf()).into(imgFm);
        }
        if(!TextUtils.isEmpty(detailsBean.getBookimgz())){
            Glide.with(this).load(detailsBean.getBookimgz()).into(imgHk1);
        }
        if(!TextUtils.isEmpty(detailsBean.getBookimgf())){
            Glide.with(this).load(detailsBean.getBookimgf()).into(imgHk2);
        }
        if(!TextUtils.isEmpty(detailsBean.getAdmissionimg())){
            Glide.with(this).load(detailsBean.getAdmissionimg()).into(imgNotice);
        }
        if(!TextUtils.isEmpty(detailsBean.getRelatedimg())){
            Glide.with(this).load(detailsBean.getRelatedimg()).into(imgOther);
        }
        scrollView.scrollTo(0,0);
    }

}
