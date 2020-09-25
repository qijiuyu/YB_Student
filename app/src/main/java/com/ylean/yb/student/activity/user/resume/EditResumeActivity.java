package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.min.AddEducationActivity;
import com.ylean.yb.student.adapter.user.resume.AddResumeCertificateAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeEducationAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeHonorAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumePositionAdapter;
import com.ylean.yb.student.adapter.user.resume.EditResumeSpecialtyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.ylean.yb.student.view.SelectProvince;
import com.ylean.yb.student.view.SelectSalaryView;
import com.ylean.yb.student.view.SelectWorkTypeView;
import com.ylean.yb.student.view.TagsLayout;
import com.zxdc.utils.library.bean.AddHonor;
import com.zxdc.utils.library.bean.AddResumeCertificate;
import com.zxdc.utils.library.bean.AddResumePostion;
import com.zxdc.utils.library.bean.AddResumeSpecialty;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑简历
 */
public class EditResumeActivity extends BaseActivity{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_nationality)
    TextView tvNationality;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.tv_province1)
    TextView tvProvince1;
    @BindView(R.id.tv_city1)
    TextView tvCity1;
    @BindView(R.id.tv_area1)
    TextView tvArea1;
    @BindView(R.id.tv_position)
    TagsLayout tvPosition;
    @BindView(R.id.tv_industry)
    TagsLayout tvIndustry;
    @BindView(R.id.et_introduce)
    EditText etIntroduce;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_job_type)
    TextView tvJobType;
    @BindView(R.id.list_education)
    RecyclerView listEducation;
    @BindView(R.id.list_honor)
    RecyclerView listHonor;
    @BindView(R.id.list_position)
    RecyclerView listPosition;
    @BindView(R.id.list_specialty)
    RecyclerView listSpecialty;
    @BindView(R.id.list_certificate)
    RecyclerView listCertificate;
    @BindView(R.id.img_file)
    ImageView imgFile;
    //简历对象
    private ResumeBean.Resume resume;
    //选择的求职职位
    private List<ResumePostion.Position> selectPosition=new ArrayList<>();
    //选择的行业类型
    private List<DictBean.Dict> selectIndustry=new ArrayList<>();

    private AddResumeEducationAdapter educationAdapter;
    private AddResumeHonorAdapter honorAdapter;
    private AddResumePositionAdapter positionAdapter;
    private EditResumeSpecialtyAdapter specialtyAdapter;
    private AddResumeCertificateAdapter certificateAdapter;
    //在校荣誉集合
    private List<AddHonor> honorList=new ArrayList<>();
    //校内职务集合
    private List<AddResumePostion> positionList=new ArrayList<>();
    //技能特长集合
    private List<AddResumeSpecialty> specialtyList=new ArrayList<>();
    //证书集合
    private List<AddResumeCertificate> certificateList=new ArrayList<>();

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_resume;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("编辑简历");
        tvRight.setText("完成");
        resume= (ResumeBean.Resume) getIntent().getSerializableExtra("resume");

        //展示用户基本信息
        showUserBase();

        listHonor.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listHonor.setAdapter(honorAdapter=new AddResumeHonorAdapter(this,honorList));

        listPosition.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listPosition.setAdapter(positionAdapter=new AddResumePositionAdapter(this,positionList));

        listSpecialty.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listSpecialty.setAdapter(specialtyAdapter=new EditResumeSpecialtyAdapter(this,resume));

        //展示证书信息
        listCertificate.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listCertificate.setAdapter(certificateAdapter=new AddResumeCertificateAdapter(this,resume));
    }

    @OnClick({R.id.lin_back, R.id.tv_province, R.id.tv_city, R.id.tv_area,R.id.tv_province1, R.id.tv_city1, R.id.tv_area1,R.id.tv_add_position_tag,R.id.tv_add_industry_tag,R.id.tv_salary,R.id.tv_work_time,R.id.tv_job_type,R.id.tv_add_education,R.id.tv_add_honor,R.id.tv_add_position,R.id.tv_add_specialty,R.id.tv_add_certificate,R.id.img_file,R.id.tv_right})
    public void onViewClicked(View view) {
        final String province=tvProvince.getText().toString().trim();
        final String city=tvCity.getText().toString().trim();
        final String area=tvArea.getText().toString().trim();
        final String province1=tvProvince1.getText().toString().trim();
        final String city1=tvCity1.getText().toString().trim();
        final String area1=tvArea1.getText().toString().trim();
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            //选择省
            case R.id.tv_province:
                new SelectProvince(this, 0, null, new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvProvince.setText(listBean.getName());
                        tvProvince.setTag(listBean.getCode());
                        tvCity.setText(null);
                        tvArea.setText(null);
                    }
                }).show();
                break;
            //选择市
            case R.id.tv_city:
                if(TextUtils.isEmpty(province)){
                    ToastUtil.showLong("请先选择省");
                    return;
                }
                new SelectProvince(this, 1, (String) tvProvince.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvCity.setText(listBean.getName());
                        tvCity.setTag(listBean.getCode());
                        tvArea.setText(null);
                    }
                }).show();
                break;
            //选择区
            case R.id.tv_area:
                if(TextUtils.isEmpty(city)){
                    ToastUtil.showLong("请先选择市");
                    return;
                }
                new SelectProvince(this, 2, (String) tvCity.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvArea.setText(listBean.getName());
                        tvArea.setTag(listBean.getCode());
                    }
                }).show();
                break;
            //选择省
            case R.id.tv_province1:
                new SelectProvince(this, 0, null, new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvProvince1.setText(listBean.getName());
                        tvProvince1.setTag(listBean.getCode());
                        tvCity1.setText(null);
                        tvArea1.setText(null);
                    }
                }).show();
                break;
            //选择市
            case R.id.tv_city1:
                if(TextUtils.isEmpty(province1)){
                    ToastUtil.showLong("请先选择省");
                    return;
                }
                new SelectProvince(this, 1, (String) tvProvince1.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvCity1.setText(listBean.getName());
                        tvCity1.setTag(listBean.getCode());
                        tvArea1.setText(null);
                    }
                }).show();
                break;
            //选择区
            case R.id.tv_area1:
                if(TextUtils.isEmpty(city1)){
                    ToastUtil.showLong("请先选择市");
                    return;
                }
                new SelectProvince(this, 2, (String) tvCity1.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvArea1.setText(listBean.getName());
                        tvArea1.setTag(listBean.getCode());
                    }
                }).show();
                break;
            //选择求职职位
            case R.id.tv_add_position_tag:
                 intent.setClass(this,SelectPositionActivity.class);
                 intent.putExtra("selectPosition",JsonUtil.objectToString(selectPosition));
                 startActivityForResult(intent,800);
                 break;
            //选择行业类型
            case R.id.tv_add_industry_tag:
                 intent.setClass(this,SelectIndustryActivity.class);
                 intent.putExtra("selectIndustry",JsonUtil.objectToString(selectIndustry));
                 startActivityForResult(intent,900);
                 break;
            //选择薪资
            case R.id.tv_salary:
                 new SelectSalaryView(this,tvSalary).show();
                 break;
            //选择到岗时间
            case R.id.tv_work_time:
                 SelectTimeUtils.selectTime(this, new TimeCallBack() {
                     @Override
                     public void getTime(String time) {
                         tvWorkTime.setText(time);
                     }
                 });
                 break;
            //选择工作类型
            case R.id.tv_job_type:
                 new SelectWorkTypeView(this,tvJobType).show();
                 break;
            //添加教育经历
            case R.id.tv_add_education:
                setClass(AddEducationActivity.class,1000);
                break;
            //添加在校荣誉
            case R.id.tv_add_honor:
                setClass(AddSchoolHonorActivity.class,1001);
                 break;
            //添加校内职务
            case R.id.tv_add_position:
                setClass(AddSchoolPositionActivity.class,1002);
                 break;
            //添加技能特长
            case R.id.tv_add_specialty:
                 intent.setClass(this,AddSpecialtyActivity.class);
                 intent.putExtra("resume",resume);
                 startActivityForResult(intent,1003);
                 break;
            //添加证书
            case R.id.tv_add_certificate:
                 intent.setClass(this,AddCertificateActivity.class);
                 intent.putExtra("resume",resume);
                 startActivityForResult(intent,1004);
                 break;
            //添加附件
            case R.id.img_file:
                 SelectPhotoUtil.SelectPhoto(this,1);
                 break;
            case R.id.tv_right:
                 break;
            default:
                break;
        }
    }


    ViewGroup.MarginLayoutParams lp;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhotoUtil.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhotoUtil.pai);
                    Glide.with(this).load(tempFile).into(imgFile);
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                Glide.with(this).load(list.get(0).getCompressPath()).into(imgFile);
                break;
            default:
                break;
        }
        switch (resultCode){
            //获取选择的职位
            case 800:
                 selectPosition=JsonUtil.stringToList(data.getStringExtra("position"),ResumePostion.Position.class);
                 tvPosition.removeAllViews();
                 lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                 for (int i=0;i<selectPosition.size();i++){
                     TextView textView = new TextView(this);
                     textView.setText(selectPosition.get(i).getPositionName());
                     textView.setTextColor(getResources().getColor(android.R.color.black));
                     textView.setTextSize(13);
                     textView.setBackgroundResource(R.drawable.bg_tag_history);
                     textView.setPadding(10, 10, 10, 10);
                     textView.setGravity(Gravity.CENTER);
                     tvPosition.addView(textView, lp);
                 }
                 break;
            //获取选择的行业类型
            case 900:
                selectIndustry=JsonUtil.stringToList(data.getStringExtra("dict"),DictBean.Dict.class);
                tvIndustry.removeAllViews();
                lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                for (int i=0;i<selectIndustry.size();i++){
                    TextView textView = new TextView(this);
                    textView.setText(selectIndustry.get(i).getName());
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                    textView.setTextSize(13);
                    textView.setBackgroundResource(R.drawable.bg_tag_history);
                    textView.setPadding(10, 10, 10, 10);
                    textView.setGravity(Gravity.CENTER);
                    tvIndustry.addView(textView, lp);
                }
                 break;
             default:
                 break;
        }
    }


    /**
     * 展示用户基本信息
     */
    private void showUserBase(){
        final UserInfo userInfo= (UserInfo) SPUtil.getInstance(this).getObject(SPUtil.USER_BASE_INFO,UserInfo.class);
        if(userInfo==null){
            return;
        }
        if(!TextUtils.isEmpty(userInfo.getData().getPhoto())){
            Glide.with(this).load(userInfo.getData().getPhoto()).into(imgHead);
        }
        tvName.setText(userInfo.getData().getName());
        tvNationality.setText(userInfo.getData().getNationality());
        tvNational.setText(userInfo.getData().getNation());
        tvBirthday.setText("出生日期："+userInfo.getData().getBirthday());
        etMobile.setText("联系电话："+userInfo.getData().getPhone());
        etEmail.setText("邮箱："+userInfo.getData().getEmail());
        etWx.setText("微信号："+userInfo.getData().getWechat());
        etQq.setText("QQ："+userInfo.getData().getQq());

        /**
         * 家庭地址
         */
        if(!TextUtils.isEmpty(userInfo.getData().getResidenceaddress())){
            final Address address= (Address) JsonUtil.stringToObject(userInfo.getData().getResidenceaddress(),Address.class);
            tvProvince.setText(address.getPname());
            tvProvince.setTag(address.getPcode());
            tvCity.setText(address.getCname());
            tvCity.setTag(address.getCcode());
            tvArea.setText(address.getAname());
            tvArea.setTag(address.getAcode());
            etAddress.setText("现居住地址："+address.getAddress());
        }
    }
}
