package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.AddEducationActivity;
import com.ylean.yb.student.adapter.user.resume.AddResumeCertificateAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeEducationAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeHonorAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumePositionAdapter;
import com.ylean.yb.student.adapter.user.resume.AddResumeSpecialtyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.bean.AddEducation;
import com.zxdc.utils.library.bean.AddHonor;
import com.zxdc.utils.library.bean.AddResumeCertificate;
import com.zxdc.utils.library.bean.AddResumePostion;
import com.zxdc.utils.library.bean.AddResumeSpecialty;
import com.zxdc.utils.library.view.CircleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑简历
 */
public class EditResumeActivity extends BaseActivity {
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
    @BindView(R.id.et_salary)
    EditText etSalary;
    @BindView(R.id.tv_province1)
    TextView tvProvince1;
    @BindView(R.id.tv_city1)
    TextView tvCity1;
    @BindView(R.id.tv_area1)
    TextView tvArea1;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
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
    private AddResumeEducationAdapter educationAdapter;
    private AddResumeHonorAdapter honorAdapter;
    private AddResumePositionAdapter positionAdapter;
    private AddResumeSpecialtyAdapter specialtyAdapter;
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

        listHonor.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listHonor.setAdapter(honorAdapter=new AddResumeHonorAdapter(this,honorList));

        listPosition.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listPosition.setAdapter(positionAdapter=new AddResumePositionAdapter(this,positionList));

        listSpecialty.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listSpecialty.setAdapter(specialtyAdapter=new AddResumeSpecialtyAdapter(this,specialtyList));

        listCertificate.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listCertificate.setAdapter(certificateAdapter=new AddResumeCertificateAdapter(this,certificateList));
    }

    @OnClick({R.id.lin_back, R.id.tv_add_education,R.id.tv_add_honor,R.id.tv_add_position,R.id.tv_add_specialty,R.id.tv_add_certificate,R.id.img_file,R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
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
                setClass(AddSpecialtyActivity.class,1003);
                 break;
            //添加证书
            case R.id.tv_add_certificate:
                setClass(AddCertificateActivity.class,1004);
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
    }
}
