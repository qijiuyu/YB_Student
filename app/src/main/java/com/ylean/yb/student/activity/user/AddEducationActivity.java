package com.ylean.yb.student.activity.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.persenter.EducationP;
import com.ylean.yb.student.persenter.SchoolP;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.ylean.yb.student.view.SelectFacultyView;
import com.ylean.yb.student.view.SelectProvince;
import com.ylean.yb.student.view.SelectSchoolType;
import com.ylean.yb.student.view.SelectSchoolView;
import com.zxdc.utils.library.bean.AddEducation;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加教育经历
 */
public class AddEducationActivity extends BaseActivity  implements SchoolP.Face, EducationP.Face {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_faculty)
    TextView tvFaculty;
    @BindView(R.id.tv_specialty)
    TextView tvSpecialty;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rel)
    RelativeLayout rel;
    private SchoolP schoolP;
    private EducationP educationP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_add_education;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        schoolP=new SchoolP(this,this);
        educationP=new EducationP(this,this);
    }


    @OnClick({R.id.tv_type, R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_faculty, R.id.tv_specialty, R.id.tv_school, R.id.tv_time, R.id.tv_add})
    public void onViewClicked(View view) {
        final String province=tvProvince.getText().toString().trim();
        final String city=tvCity.getText().toString().trim();
        final String area=tvArea.getText().toString().trim();
        switch (view.getId()) {
            //选择学校类型
            case R.id.tv_type:
                new SelectSchoolType(this, new SelectRelationCallBack() {
                    public void onSuccess(Object object, Object object2) {
                        tvType.setText((String)object);
                        tvType.setTag(object2);
                    }
                }).show();
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
            //选择所在院系
            case R.id.tv_faculty:
                schoolP.getFacultyList();
                break;
            //选择所在专业
            case R.id.tv_specialty:
                schoolP.getSpecialtyList();
                break;
            //选择所在学校
            case R.id.tv_school:
                schoolP.getSchoolList();
                break;
            //入学时间
            case R.id.tv_time:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        tvTime.setText(time);
                    }
                });
                break;
            case R.id.tv_add:
                final String schoolType=tvType.getText().toString().trim();
                final String school=tvSchool.getText().toString().trim();
                final String faculty=tvFaculty.getText().toString().trim();
                final String specialty=tvSpecialty.getText().toString().trim();
                final String time=tvTime.getText().toString().trim();
                if(TextUtils.isEmpty(schoolType)){
                    ToastUtil.showLong("请选择学校类型");
                    return;
                }
                if(TextUtils.isEmpty(province)){
                    ToastUtil.showLong("请选择所在省");
                    return;
                }
                if(TextUtils.isEmpty(city)){
                    ToastUtil.showLong("请选择所在市");
                    return;
                }
                if(TextUtils.isEmpty(area)){
                    ToastUtil.showLong("请选择所在区");
                    return;
                }
                if(TextUtils.isEmpty(school)){
                    ToastUtil.showLong("请选择所在学校");
                    return;
                }
                if(TextUtils.isEmpty(faculty)){
                    ToastUtil.showLong("请选择院系名称");
                    return;
                }
                if(TextUtils.isEmpty(specialty)){
                    ToastUtil.showLong("请选择专业名称");
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择入学时间");
                    return;
                }
                AddEducation addEducation=new AddEducation();
                addEducation.setType((int)tvType.getTag());
                AddEducation.Region region=new AddEducation.Region();
                region.setPname(province);
                region.setPcode((String)tvProvince.getTag());
                region.setPname(province);
                region.setCcode((String)tvCity.getTag());
                region.setCname(city);
                region.setAcode((String)tvArea.getTag());
                region.setAname(area);
                addEducation.setRegion(region);
                addEducation.setSid((int)tvSchool.getTag());
                addEducation.setFacultyid((int)tvFaculty.getTag());
                addEducation.setMajorid((int)tvSpecialty.getTag());
                addEducation.setAdmissiontime(time);
                List<AddEducation> list=new ArrayList<>();
                list.add(addEducation);
                educationP.addEducation(JsonUtil.objectToString(list));
                break;
            default:
                break;
        }
    }


    /**
     * 获取所有学校
     * @param list
     */
    @Override
    public void getSchoolList(List<SchoolBean.School> list) {
        new SelectSchoolView(this,list,tvSchool).show();
    }


    /**
     * 获取所有院系
     * @param list
     */
    @Override
    public void getFacultyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(this,list,tvFaculty).show();
    }


    /**
     * 获取所有专业
     * @param list
     */
    @Override
    public void getSpecialtyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(this,list,tvSpecialty).show();
    }


    /**
     * 添加成功
     */
    @Override
    public void addSuccess() {

    }
}
