package com.ylean.yb.student.activity.user.resume;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.MyCertificateAdapter;
import com.ylean.yb.student.adapter.user.resume.MyEducationAdapter;
import com.ylean.yb.student.adapter.user.resume.MyHonorAdapter;
import com.ylean.yb.student.adapter.user.resume.MyPositionAdapter;
import com.ylean.yb.student.adapter.user.resume.MySpecialtyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyResumeP;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.Salary;
import com.zxdc.utils.library.bean.Speciality;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的简历
 */
public class MyResumeActivity extends BaseActivity implements MyResumeP.Face {
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
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.tv_job_address)
    TextView tvJobAddress;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_job_type)
    TextView tvJobType;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_work_type)
    TextView tvWorkType;
    @BindView(R.id.list_education)
    MeasureListView listEducation;
    @BindView(R.id.list_honor)
    MeasureListView listHonor;
    @BindView(R.id.list_position)
    MeasureListView listPosition;
    @BindView(R.id.list_specialty)
    MeasureListView listSpecialty;
    @BindView(R.id.list_certificate)
    MeasureListView listCertificate;

    private MyResumeP myResumeP=new MyResumeP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_resume;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的简历");
        tvRight.setText("投递记录");

        //展示用户基本信息
        showUserBase();

        //查询我的简历
        myResumeP.getMyResume();
    }


    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                 setClass(DeliveryRecordActivity.class);
                break;
            case R.id.tv_edit:
                setClass(EditResumeActivity.class);
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
        tvMobile.setText("联系电话："+userInfo.getData().getPhone());
        tvEmail.setText("邮箱："+userInfo.getData().getEmail());
        tvWx.setText("微信号："+userInfo.getData().getWechat());
        tvQq.setText("QQ："+userInfo.getData().getQq());
        if (!TextUtils.isEmpty(userInfo.getData().getResidenceaddress())) {
            final Address address = (Address) JsonUtil.stringToObject(userInfo.getData().getResidenceaddress(), Address.class);
            tvAddress.setText("现居住地址："+address.getAddress());
        }
    }


    /**
     * 查询我的简历
     * @param resume
     */
    @Override
    public void getMyResume(ResumeBean.Resume resume) {
        if(!TextUtils.isEmpty(resume.getExpectedCapital())){
            final Salary salary= (Salary) JsonUtil.stringToObject(resume.getExpectedCapital(),Salary.class);
            tvSalary.setText("期望薪资："+salary.getMin()+"-"+salary.getMax()+"/月");
        }
        if(!TextUtils.isEmpty(resume.getWorkPlace())){
            final Address address = (Address) JsonUtil.stringToObject(resume.getWorkPlace(), Address.class);
            tvJobAddress.setText("工作地点："+address.getPname()+","+address.getCname()+","+address.getAname());
        }
        tvIntroduce.setText("自我介绍："+resume.getIntroduce());
        tvWorkTime.setText("到岗时间："+resume.getArrivalTime());
        switch (resume.getdType()){
            case 10:
                 tvWorkType.setText("工作类型：全职");
                 break;
            case 20:
                tvWorkType.setText("工作类型：兼职");
                break;
            case 30:
                tvWorkType.setText("工作类型：实习");
                break;
            default:
                break;
        }

        //学习经历
        listEducation.setAdapter(new MyEducationAdapter(this,resume.getLearningExperienceList()));

        //在校荣誉
        listHonor.setAdapter(new MyHonorAdapter(this,resume.getInSchoolHonorList()));

        //校内职务
        listPosition.setAdapter(new MyPositionAdapter(this,resume.getSchoolDutiesList()));

        //技能特长
        if(!TextUtils.isEmpty(resume.getSpeciality())){
            List<Speciality> list=JsonUtil.stringToList(resume.getSpeciality(),Speciality.class);
            listSpecialty.setAdapter(new MySpecialtyAdapter(this,list));
        }

        //证书
        listCertificate.setAdapter(new MyCertificateAdapter(this,resume.getCertificatesList()));
    }
}
