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
import com.ylean.yb.student.adapter.user.resume.AddResumeCertificateAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeEducationAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeHonorAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumePositionAdapter;
import com.ylean.yb.student.adapter.user.resume.EditResumeSpecialtyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.persenter.user.MyResumeP;
import com.ylean.yb.student.persenter.user.SettingP;
import com.ylean.yb.student.persenter.user.UserP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.ylean.yb.student.view.SelectProvince;
import com.ylean.yb.student.view.SelectSalaryView;
import com.ylean.yb.student.view.SelectWorkTypeView;
import com.ylean.yb.student.view.TagsLayout;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.bean.Salary;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.JobIntention;
import com.zxdc.utils.library.bean.parameter.ResumeBase;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
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
public class EditResumeActivity extends BaseActivity implements UserP.Face,MyResumeP.Face2, SettingP.Face,MyResumeP.Face3 {
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
    /**
     * 1：选择头像
     * 2：选择附件
     */
    private int selectImg=1;

    private AddResumeEducationAdapter educationAdapter;
    private AddResumeHonorAdapter honorAdapter;
    private AddResumePositionAdapter positionAdapter;
    private EditResumeSpecialtyAdapter specialtyAdapter;
    private AddResumeCertificateAdapter certificateAdapter;

    private MyResumeP myResumeP=new MyResumeP(this);
    private SettingP settingP=new SettingP(this);

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
        myResumeP.setFace2(this);
        myResumeP.setFace3(this);
        settingP.setFace(this);
        tvTitle.setText("编辑简历");
        tvRight.setText("完成");
        resume= (ResumeBean.Resume) getIntent().getSerializableExtra("resume");

        //展示用户基本信息
        showUserBase();
    }

    @OnClick({R.id.lin_back,R.id.img_head, R.id.tv_province, R.id.tv_city, R.id.tv_area,R.id.tv_save_base,R.id.tv_province1, R.id.tv_city1, R.id.tv_area1,R.id.tv_add_position_tag,R.id.tv_add_industry_tag,R.id.tv_salary,R.id.tv_work_time,R.id.tv_job_type,R.id.tv_save,R.id.tv_add_education,R.id.tv_add_honor,R.id.tv_add_position,R.id.tv_add_specialty,R.id.tv_add_certificate,R.id.img_file,R.id.tv_right})
    public void onViewClicked(View view) {
        final String province=tvProvince.getText().toString().trim();
        final String city=tvCity.getText().toString().trim();
        final String area=tvArea.getText().toString().trim();
        final String province1=tvProvince1.getText().toString().trim();
        final String city1=tvCity1.getText().toString().trim();
        final String area1=tvArea1.getText().toString().trim();
        Intent intent=new Intent();
        Address address=null;
        switch (view.getId()) {
            case R.id.lin_back:
                break;
            //选择头像
            case R.id.img_head:
                selectImg=1;
                SelectPhotoUtil.SelectPhoto(this,1);
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
            //保存基本信息
            case R.id.tv_save_base:
                 String mobile=etMobile.getText().toString().trim();
                 String mail=etEmail.getText().toString().trim();
                 String wx=etWx.getText().toString().trim();
                 String qq=etQq.getText().toString().trim();
                 String strAddress=etAddress.getText().toString().trim();
                 if(TextUtils.isEmpty(mobile)){
                     ToastUtil.showLong("请输入联系电话");
                     return;
                 }
                if(TextUtils.isEmpty(mail)){
                    ToastUtil.showLong("请输入邮箱");
                    return;
                }
                if(TextUtils.isEmpty(wx)){
                    ToastUtil.showLong("请输入微信");
                    return;
                }
                if(TextUtils.isEmpty(qq)){
                    ToastUtil.showLong("请输入qq");
                    return;
                }
                if (TextUtils.isEmpty(province)) {
                    ToastUtil.showLong("请选择所在省");
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    ToastUtil.showLong("请选择所在市");
                    return;
                }
                if (TextUtils.isEmpty(area)) {
                    ToastUtil.showLong("请选择所在区");
                    return;
                }
                if(TextUtils.isEmpty(strAddress)){
                    ToastUtil.showLong("请输入详细居住地址");
                    return;
                }

                ResumeBase resumeBase=new ResumeBase();
                address=new Address();//居住地点
                address.setPcode((String) tvProvince.getTag());
                address.setPname(province);
                address.setCcode((String) tvCity.getTag());
                address.setCname(city);
                address.setAcode((String) tvArea.getTag());
                address.setAname(area);
                resumeBase.setCurrentResidence(address);

                resumeBase.setId(resume.getId());
                if(imgHead.getTag()!=null){
                    resumeBase.setImg((String) imgHead.getTag());
                }
                resumeBase.setMail(mail);
                resumeBase.setPhone(mobile);
                resumeBase.setWx(wx);
                resumeBase.setQq(qq);

                LogUtils.e("+++++++++"+JsonUtil.objectToString(resumeBase));
                myResumeP.saveOrUpdateResumePerson(resumeBase);
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
            //保存基本信息
            case R.id.tv_save:
                 final String salary=tvSalary.getText().toString().trim();
                 final String introduce=etIntroduce.getText().toString().trim();
                 final String workTime=tvWorkTime.getText().toString().trim();
                 final String jobType=tvJobType.getText().toString().trim();
                 if(TextUtils.isEmpty(salary)){
                     ToastUtil.showLong("请选择期望薪资");
                     return;
                 }
                if (TextUtils.isEmpty(province1)) {
                    ToastUtil.showLong("请选择所在省");
                    return;
                }
                if (TextUtils.isEmpty(city1)) {
                    ToastUtil.showLong("请选择所在市");
                    return;
                }
                if (TextUtils.isEmpty(area1)) {
                    ToastUtil.showLong("请选择所在区");
                    return;
                }
                if(selectPosition.size()==0){
                    ToastUtil.showLong("请选择求职职位");
                    return;
                }
                if(selectIndustry.size()==0){
                    ToastUtil.showLong("请选择求职行业");
                    return;
                }
                if(TextUtils.isEmpty(introduce)){
                    ToastUtil.showLong("请输入自我介绍");
                    return;
                }
                if(TextUtils.isEmpty(workTime)){
                    ToastUtil.showLong("请选择到岗时间");
                    return;
                }
                if(TextUtils.isEmpty(jobType)){
                    ToastUtil.showLong("请选择工作类型");
                    return;
                }
                try {
                    JobIntention jobIntention=new JobIntention();
                    jobIntention.setArrivalTime(workTime);//到岗时间
                    jobIntention.setdType((int)tvJobType.getTag());//工作类型

                    Salary salaryBean=new Salary();//期望薪资
                    salaryBean.setMin((Integer) tvSalary.getTag(R.id.tag1));
                    salaryBean.setMax((Integer) tvSalary.getTag(R.id.tag2));
                    jobIntention.setExpectedCapital(salaryBean);

                    jobIntention.setId(resume.getId());//简历id
                    jobIntention.setIntroduce(introduce);//自我介绍

                    List<JobIntention.Industry> industryList=new ArrayList<>();//求职行业
                    for (int i=0;i<selectIndustry.size();i++){
                        JobIntention.Industry industry=new JobIntention.Industry();
                        industry.setIndustryTypeId(selectIndustry.get(i).getId());
                        industry.setIndustryTypeName(selectIndustry.get(i).getName());
                        industryList.add(industry);
                    }
                    jobIntention.setJobIndustryPOJOS(industryList);

                    List<JobIntention.Position> positionList=new ArrayList<>();//求职职位
                    for (int i=0;i<selectPosition.size();i++){
                        JobIntention.Position position=new JobIntention.Position();
                        position.setPositionTypeId(selectPosition.get(i).getId());
                        positionList.add(position);
                    }
                    jobIntention.setJobPositionTypePOJOS(positionList);

                    address=new Address();//工作地点
                    address.setPcode((String) tvProvince1.getTag());
                    address.setPname(province1);
                    address.setCcode((String) tvCity1.getTag());
                    address.setCname(city1);
                    address.setAcode((String) tvArea1.getTag());
                    address.setAname(area1);
                    jobIntention.setWorkPlace(address);

                    LogUtils.e("+++++++++++"+JsonUtil.objectToString(jobIntention));
                    myResumeP.saveOrUpdateJobIdea(jobIntention);
                }catch (Exception e){
                    e.printStackTrace();
                }
                 break;
            //添加教育经历
            case R.id.tv_add_education:
                intent.setClass(this, AddEducationActivity.class);
                intent.putExtra("resume",resume);
                startActivityForResult(intent,1000);
                break;
            //添加在校荣誉
            case R.id.tv_add_honor:
                intent.setClass(this,AddSchoolHonorActivity.class);
                intent.putExtra("resume",resume);
                startActivityForResult(intent,1001);
                 break;
            //添加校内职务
            case R.id.tv_add_position:
                intent.setClass(this,AddSchoolPositionActivity.class);
                intent.putExtra("resume",resume);
                startActivityForResult(intent,1002);
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
                 selectImg=2;
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
                    final File tempFile = new File(SelectPhotoUtil.pai);
                    if(selectImg==1){
                        //上传头像
                        List<FileBean> fileList=new ArrayList<FileBean>(){{add(new FileBean("photo",tempFile));}};
                        settingP.uploadImg(fileList);

                    }else{
                        Glide.with(this).load(tempFile).into(imgHead);
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                final List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                if(selectImg==1){
                    //上传头像
                    List<FileBean> fileList=new ArrayList<FileBean>(){{add(new FileBean("photo",new File(list.get(0).getCompressPath())));}};
                    settingP.uploadImg(fileList);

                }else{
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgHead);
                }
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
                     textView.setText(selectPosition.get(i).getName());
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
    @Override
    public void getbaseinfo(UserInfo userInfo) {

    }


    /**
     * 展示用户基本信息
     */
    private void showUserBase(){
        if(resume==null){
            return;
        }
        try {
            /**
             * 基本信息
             */
            Glide.with(this).load(resume.getImg()).into(imgHead);
            imgHead.setTag(resume.getImg());
            if(resume.getStudentVO()!=null){
                tvName.setText(resume.getStudentVO().getName());
                tvNationality.setText(resume.getStudentVO().getNationality());
                tvNational.setText(resume.getStudentVO().getNation());
                tvBirthday.setText("出生日期："+resume.getStudentVO().getBirthday());
                etMobile.setText(resume.getPhone());
                etEmail.setText(resume.getMail());
                etWx.setText(resume.getWx());
                etQq.setText(resume.getQq());
                if (!TextUtils.isEmpty(resume.getStudentVO().getAddress())) {
                    final Address address = (Address) JsonUtil.stringToObject(resume.getStudentVO().getAddress(), Address.class);
                    tvProvince.setText(address.getPname());
                    tvProvince.setTag(address.getPcode());
                    tvCity.setText(address.getCname());
                    tvCity.setTag(address.getCcode());
                    tvArea.setText(address.getAname());
                    tvArea.setTag(address.getAcode());
                    etAddress.setText("现居住地址："+address.getAddress());
                }
            }



            //期望薪资
            if(!TextUtils.isEmpty(resume.getExpectedCapital())) {
                final Salary salary = (Salary) JsonUtil.stringToObject(resume.getExpectedCapital(), Salary.class);
                tvSalary.setText(salary.getMin()+"-"+salary.getMax()+"元/月");
                tvSalary.setTag(R.id.tag1,salary.getMin());
                tvSalary.setTag(R.id.tag2,salary.getMax());
            }

            //工作地点
            if(!TextUtils.isEmpty(resume.getWorkPlace())) {
                final Address address = (Address) JsonUtil.stringToObject(resume.getWorkPlace(), Address.class);
                tvProvince1.setText(address.getPname());
                tvProvince1.setTag(address.getPcode());
                tvCity1.setText(address.getCname());
                tvCity1.setTag(address.getCcode());
                tvArea1.setText(address.getAname());
                tvArea1.setTag(address.getAcode());
            }

            lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if(resume.getPositionTypeList()!=null){
                tvPosition.removeAllViews();
                for (int i=0;i<resume.getPositionTypeList().size();i++){
                    ResumePostion.Position position=new ResumePostion.Position();
                    position.setId(resume.getPositionTypeList().get(i).getId());
                    position.setName(resume.getPositionTypeList().get(i).getName());
                    selectPosition.add(position);
                }
                for (int i=0;i<selectPosition.size();i++){
                    TextView textView = new TextView(this);
                    textView.setText(selectPosition.get(i).getName());
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                    textView.setTextSize(13);
                    textView.setBackgroundResource(R.drawable.bg_tag_history);
                    textView.setPadding(10, 10, 10, 10);
                    textView.setGravity(Gravity.CENTER);
                    tvPosition.addView(textView, lp);
                }
            }

            if(resume.getJobIndustryList()!=null){
                tvIndustry.removeAllViews();
                for (int i=0;i<resume.getJobIndustryList().size();i++){
                    DictBean.Dict dict=new DictBean.Dict();
                    dict.setId(resume.getJobIndustryList().get(i).getId());
                    dict.setName(resume.getJobIndustryList().get(i).getIndustryTypeName());
                    selectIndustry.add(dict);
                }
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
            }

            etIntroduce.setText(resume.getIntroduce());
            tvWorkTime.setText(resume.getArrivalTime());
            tvJobType.setTag(resume.getdType());
            switch (resume.getdType()){
                case 10:
                    tvJobType.setText("全职");
                    break;
                case 20:
                    tvJobType.setText("兼职");
                    break;
                case 30:
                    tvJobType.setText("实习");
                    break;
                default:
                    break;
            }


            //展示学习经历
            listEducation.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            listEducation.setAdapter(educationAdapter=new AddResumeEducationAdapter(this,resume));

            //展示在校荣誉
            listHonor.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            listHonor.setAdapter(honorAdapter=new AddResumeHonorAdapter(this,resume));

            listPosition.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            listPosition.setAdapter(positionAdapter=new AddResumePositionAdapter(this,resume));

            //展示特长信息
            listSpecialty.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            listSpecialty.setAdapter(specialtyAdapter=new EditResumeSpecialtyAdapter(this,resume));

            //展示证书信息
            listCertificate.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            listCertificate.setAdapter(certificateAdapter=new AddResumeCertificateAdapter(this,resume));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 头像上传成功
     */
    @Override
    public void uploadSuccess(final String imgPath) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(activity).load(imgPath).into(imgHead);
                imgHead.setTag(imgPath);
            }
        });
    }


    /**
     * 编辑基本信息成功
     */
    @Override
    public void baseSuccess() {

    }


    /**
     * 编辑求职意向成功
     */
    @Override
    public void onSuccess() {

    }

}
