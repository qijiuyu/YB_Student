package com.ylean.yb.student.activity.user.resume;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.MyCertificateAdapter;
import com.ylean.yb.student.adapter.user.resume.MyEducationAdapter;
import com.ylean.yb.student.adapter.user.resume.MyHonorAdapter;
import com.ylean.yb.student.adapter.user.resume.MyPositionAdapter;
import com.ylean.yb.student.adapter.user.resume.MySpecialtyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的简历
 */
public class MyResumeActivity extends BaseActivity {
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

        listEducation.setAdapter(new MyEducationAdapter(this));
        listHonor.setAdapter(new MyHonorAdapter(this));
        listPosition.setAdapter(new MyPositionAdapter(this));
        listSpecialty.setAdapter(new MySpecialtyAdapter(this));
        listCertificate.setAdapter(new MyCertificateAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.tv_edit:
                break;
            default:
                break;
        }
    }
}
