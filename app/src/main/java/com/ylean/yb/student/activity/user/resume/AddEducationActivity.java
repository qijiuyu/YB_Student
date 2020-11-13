package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.persenter.SchoolP;
import com.ylean.yb.student.persenter.user.MyResumeP;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.ylean.yb.student.view.SelectEducationView;
import com.ylean.yb.student.view.SelectFacultyView;
import com.ylean.yb.student.view.SelectProvince;
import com.ylean.yb.student.view.SelectSchoolType;
import com.ylean.yb.student.view.SelectSchoolView;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.bean.parameter.AddResumeEducation;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加教育经历
 */
public class AddEducationActivity extends BaseActivity implements SchoolP.Face, MyResumeP.Face2 {

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
    @BindView(R.id.tv_education)
    TextView tvEducation;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.lin_faculty)
    LinearLayout linFaculty;
    @BindView(R.id.lin_specialty)
    LinearLayout linSpecialty;
    @BindView(R.id.et_class)
    EditText etClass;
    @BindView(R.id.lin_class)
    LinearLayout linClass;
    //简历对象
    private ResumeBean.Resume resume;
    //要编辑的位置
    private int position;

    private SchoolP schoolP = new SchoolP(this, this);
    private MyResumeP myResumeP=new MyResumeP(this);

    /**
     * 加载布局
     *
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
        myResumeP.setFace2(this);


        //获取简历对象
        resume= (ResumeBean.Resume) getIntent().getSerializableExtra("resume");
        //获取要编辑的位置
        position=getIntent().getIntExtra("position",-1);
        if(position!=-1){
            final ResumeBean.Education education=resume.getLearningExperienceList().get(position);
            if(education.getType()==3){
                etClass.setVisibility(View.GONE);
                tvFaculty.setVisibility(View.VISIBLE);
                tvSpecialty.setVisibility(View.VISIBLE);
            }else{
                etClass.setVisibility(View.VISIBLE);
                tvFaculty.setVisibility(View.GONE);
                tvSpecialty.setVisibility(View.GONE);
            }
            switch (education.getType()){
                case 0:
                    tvType.setText("高中");
                    break;
                case 1:
                    tvType.setText("中职");
                    break;
                case 2:
                    tvType.setText("高职");
                    break;
                case 3:
                    tvType.setText("大学");
                    break;
                default:
                    break;
            }
            tvType.setTag(education.getType());
            switch (education.getEducation()){
                case 0:
                    tvEducation.setText("高中");
                    break;
                case 1:
                    tvEducation.setText("中职");
                    break;
                case 2:
                    tvEducation.setText("高职");
                    break;
                case 3:
                    tvEducation.setText("大学专科");
                    break;
                case 4:
                    tvEducation.setText("大学本科");
                    break;
                case 5:
                    tvEducation.setText("硕士");
                    break;
                case 6:
                    tvEducation.setText("博士");
                    break;
                default:
                    break;
            }
            tvEducation.setTag(education.getEducation());
            if(!TextUtils.isEmpty(education.getRegion())){
                final Address address = (Address) JsonUtil.stringToObject(education.getRegion(), Address.class);
                tvProvince.setText(address.getPname());
                tvProvince.setTag(address.getPcode());
                tvCity.setText(address.getCname());
                tvCity.setTag(address.getCcode());
                tvArea.setText(address.getAname());
                tvArea.setTag(address.getAcode());
            }
            tvSchool.setText(education.getSchoolName());
            tvSchool.setTag(education.getSid());
            if(education.getType()==3){
                tvFaculty.setText(education.getFaculty());
                tvFaculty.setTag(education.getFacultyid());
                tvSpecialty.setText(education.getMajor());
                tvSpecialty.setTag(education.getMajorid());
            }else{
                etClass.setText(education.getGrades());
            }
            tvTime.setText(education.getAdmissiontime());
        }
    }


    @OnClick({R.id.rel, R.id.tv_type, R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_faculty, R.id.tv_specialty, R.id.tv_school, R.id.tv_education, R.id.tv_time, R.id.tv_add})
    public void onViewClicked(View view) {
        final String province = tvProvince.getText().toString().trim();
        final String city = tvCity.getText().toString().trim();
        final String area = tvArea.getText().toString().trim();
        switch (view.getId()) {
            case R.id.rel:
                finish();
                break;
            //选择学校类型
            case R.id.tv_type:
                new SelectSchoolType(this, new SelectRelationCallBack() {
                    public void onSuccess(Object object, Object object2) {
                        tvType.setText((String) object);
                        tvType.setTag(object2);
                        if((int)object2==3){
                            linFaculty.setVisibility(View.VISIBLE);
                            linSpecialty.setVisibility(View.VISIBLE);
                            linClass.setVisibility(View.GONE);
                        }else{
                            linFaculty.setVisibility(View.GONE);
                            linSpecialty.setVisibility(View.GONE);
                            linClass.setVisibility(View.VISIBLE);
                        }
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
                if (TextUtils.isEmpty(province)) {
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
                if (TextUtils.isEmpty(city)) {
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
            //选择学历
            case R.id.tv_education:
                new SelectEducationView(this, tvEducation).show();
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
                final String schoolType = tvType.getText().toString().trim();
                final String school = tvSchool.getText().toString().trim();
                final String faculty = tvFaculty.getText().toString().trim();
                final String specialty = tvSpecialty.getText().toString().trim();
                final String strClass=etClass.getText().toString().trim();
                final String education=tvEducation.getText().toString().trim();
                final String time = tvTime.getText().toString().trim();
                if (TextUtils.isEmpty(schoolType)) {
                    ToastUtil.showLong("请选择学校类型");
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
                if (TextUtils.isEmpty(school)) {
                    ToastUtil.showLong("请选择所在学校");
                    return;
                }
                if((int)tvType.getTag()==3){
                    if (TextUtils.isEmpty(faculty)) {
                        ToastUtil.showLong("请选择院系名称");
                        return;
                    }
                    if (TextUtils.isEmpty(specialty)) {
                        ToastUtil.showLong("请选择专业名称");
                        return;
                    }
                }else{
                    if (TextUtils.isEmpty(strClass)) {
                        ToastUtil.showLong("请输入班级");
                        return;
                    }
                }
                if (TextUtils.isEmpty(education)) {
                    ToastUtil.showLong("请选择学历");
                    return;
                }
                if (TextUtils.isEmpty(time)) {
                    ToastUtil.showLong("请选择入学时间");
                    return;
                }
                AddResumeEducation addResumeEducation=new AddResumeEducation();
                addResumeEducation.setId(resume.getId());

                List<AddResumeEducation.ObjectBean> list=new ArrayList<>();
                for (int i=0;i<resume.getLearningExperienceList().size();i++){
                    AddResumeEducation.ObjectBean objectBean=new AddResumeEducation.ObjectBean();
                    objectBean.setType(resume.getLearningExperienceList().get(i).getType());
                    objectBean.setAdmissiontime(resume.getLearningExperienceList().get(i).getAdmissiontime());
                    objectBean.setEducation(resume.getLearningExperienceList().get(i).getEducation());
                    objectBean.setFaculty(resume.getLearningExperienceList().get(i).getFaculty());
                    objectBean.setFacultyid(resume.getLearningExperienceList().get(i).getFacultyid());
                    objectBean.setGrades(resume.getLearningExperienceList().get(i).getGrades());
                    objectBean.setMajor(resume.getLearningExperienceList().get(i).getMajor());
                    objectBean.setMajorid(resume.getLearningExperienceList().get(i).getMajorid());
                    objectBean.setId(resume.getLearningExperienceList().get(i).getId());
                    objectBean.setRegion((Address) JsonUtil.stringToObject(resume.getLearningExperienceList().get(i).getRegion(),Address.class));
                    objectBean.setSid((int) tvSchool.getTag());

                    list.add(objectBean);
                }


                //判断是新增数据，还是编辑老数据
                if(position!=-1){
                    list.get(position).setType((int) tvType.getTag());
                    list.get(position).setAdmissiontime(time);
                    list.get(position).setEducation((int)tvEducation.getTag());
                    if((int)tvType.getTag()==3){
                        list.get(position).setFaculty(faculty);
                        list.get(position).setFacultyid((int)tvEducation.getTag());
                        list.get(position).setMajor(specialty);
                        list.get(position).setMajorid((int) tvSpecialty.getTag());
                    }else{
                        list.get(position).setGrades(strClass);
                    }
                    Address region = new Address();
                    region.setPname(province);
                    region.setPcode((String) tvProvince.getTag());
                    region.setPname(province);
                    region.setCcode((String) tvCity.getTag());
                    region.setCname(city);
                    region.setAcode((String) tvArea.getTag());
                    region.setAname(area);
                    list.get(position).setRegion(region);
                    list.get(position).setSid((int) tvSchool.getTag());

                }else{
                    AddResumeEducation.ObjectBean objectBean=new AddResumeEducation.ObjectBean();
                    objectBean.setType((int) tvType.getTag());
                    objectBean.setAdmissiontime(time);
                    objectBean.setEducation((int)tvEducation.getTag());
                    if((int)tvType.getTag()==3){
                        objectBean.setFaculty(faculty);
                        objectBean.setFacultyid((int)tvEducation.getTag());
                        objectBean.setMajor(specialty);
                        objectBean.setMajorid((int) tvSpecialty.getTag());
                    }else{
                        objectBean.setGrades(strClass);
                    }
                    Address region = new Address();
                    region.setPname(province);
                    region.setPcode((String) tvProvince.getTag());
                    region.setPname(province);
                    region.setCcode((String) tvCity.getTag());
                    region.setCname(city);
                    region.setAcode((String) tvArea.getTag());
                    region.setAname(area);
                    objectBean.setRegion(region);
                    objectBean.setSid((int) tvSchool.getTag());
                    list.add(objectBean);
                }
                addResumeEducation.setLearningExperiencePOJOS(list);

                LogUtils.e("++++++++++"+JsonUtil.objectToString(addResumeEducation));
                myResumeP.saveOrUpdateLearnings(addResumeEducation);
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
        new SelectSchoolView(this, list, tvSchool).show();
    }


    /**
     * 获取所有院系
     * @param list
     */
    @Override
    public void getFacultyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(this, list, tvFaculty).show();
    }


    /**
     * 获取所有专业
     * @param list
     */
    @Override
    public void getSpecialtyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(this, list, tvSpecialty).show();
    }


    /**
     * 操作成功
     */
    @Override
    public void onSuccess() {
        setResult(1000,new Intent());
        finish();
    }
}
