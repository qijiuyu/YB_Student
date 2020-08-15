package com.ylean.yb.student.activity.user;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.mine.AddEducationAdapter;
import com.ylean.yb.student.adapter.user.mine.AddFamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.bean.AddEducation;
import com.zxdc.utils.library.bean.AddFamily;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人档案
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nationality)
    TextView tvNationality;
    @BindView(R.id.tv_certificate_type)
    TextView tvCertificateType;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_card_time)
    TextView tvCardTime;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_province1)
    TextView tvProvince1;
    @BindView(R.id.tv_city1)
    TextView tvCity1;
    @BindView(R.id.tv_area1)
    TextView tvArea1;
    @BindView(R.id.et_address1)
    EditText etAddress1;
    @BindView(R.id.et_parent_mobile)
    EditText etParentMobile;
    @BindView(R.id.et_land_mobile)
    EditText etLandMobile;
    @BindView(R.id.list_family)
    RecyclerView listFamily;
    @BindView(R.id.list_education)
    RecyclerView listEducation;
    private AddFamilyAdapter addFamilyAdapter;
    private AddEducationAdapter addEducationAdapter;
    //家庭成员集合
    private List<AddFamily> familyList=new ArrayList<>();
    //教育集合
    private List<AddEducation> educationList=new ArrayList<>();

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        listFamily.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listFamily.setAdapter(addFamilyAdapter=new AddFamilyAdapter(this,familyList));

        listEducation.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listEducation.setAdapter(addEducationAdapter=new AddEducationAdapter(this,educationList));
    }

    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_province1, R.id.tv_city1, R.id.tv_area1, R.id.tv_add_family,R.id.tv_add_education,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_province:
                break;
            case R.id.tv_city:
                break;
            case R.id.tv_area:
                break;
            case R.id.tv_province1:
                break;
            case R.id.tv_city1:
                break;
            case R.id.tv_area1:
                break;
            //添加家庭成员
            case R.id.tv_add_family:
                familyList.add(new AddFamily());
                addFamilyAdapter.notifyDataSetChanged();
                break;
            //添加教育经历
            case R.id.tv_add_education:
                 educationList.add(new AddEducation());
                 addEducationAdapter.notifyDataSetChanged();
                 break;
            case R.id.tv_submit:
                 break;
            default:
                break;
        }
    }
}
