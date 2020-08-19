package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.persenter.SchoolP;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加教育经历
 */
public class AddEducationView extends Dialog implements SchoolP.Face {

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
    private Activity context;
    private SchoolP schoolP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_education);
        // 绑定初始化ButterKnife
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public AddEducationView(Activity context) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
    }


    /**
     * 初始化
     */
    private void initView() {
        schoolP=new SchoolP(context,this);
    }


    @OnClick({R.id.tv_type, R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_faculty, R.id.tv_specialty, R.id.tv_school, R.id.tv_time, R.id.tv_add})
    public void onViewClicked(View view) {
        final String province=tvProvince.getText().toString().trim();
        final String city=tvCity.getText().toString().trim();
        final String area=tvArea.getText().toString().trim();
        switch (view.getId()) {
            //选择学校类型
            case R.id.tv_type:
                 new SelectSchoolType(context, new SelectRelationCallBack() {
                     public void onSuccess(Object object, Object object2) {
                         tvType.setText((String)object);
                         tvType.setTag(object2);
                     }
                 }).show();
                break;
            //选择省
            case R.id.tv_province:
                new SelectProvince(context, 0, null, new ProvinceCallBack() {
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
                new SelectProvince(context, 1, (String) tvProvince.getTag(), new ProvinceCallBack() {
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
                new SelectProvince(context, 2, (String) tvCity.getTag(), new ProvinceCallBack() {
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
                SelectTimeUtils.selectTime(context, new TimeCallBack() {
                    public void getTime(String time) {
                        tvTime.setText(time);
                    }
                });
                break;
            case R.id.tv_add:
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
        new SelectSchoolView(context,list,tvSchool).show();
    }


    /**
     * 获取所有院系
     * @param list
     */
    @Override
    public void getFacultyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(context,list,tvFaculty).show();
    }


    /**
     * 获取所有专业
     * @param list
     */
    @Override
    public void getSpecialtyList(List<FacultyBean.Faculty> list) {
        new SelectFacultyView(context,list,tvSpecialty).show();
    }
}
