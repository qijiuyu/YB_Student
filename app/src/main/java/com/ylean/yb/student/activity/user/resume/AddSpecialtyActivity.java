package com.ylean.yb.student.activity.user.resume;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyResumeP;
import com.ylean.yb.student.view.SelectMasterView;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.parameter.AddSpecialtyP;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加技能特长
 */
public class AddSpecialtyActivity extends BaseActivity implements MyResumeP.Face3 {
    @BindView(R.id.et_language)
    EditText etLanguage;
    @BindView(R.id.tv_master)
    TextView tvMaster;
    //简历id
    private int resumeId;
    //历史集合
    private ResumeBean.Speciality speciality;
    private MyResumeP myResumeP=new MyResumeP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_specialty;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        resumeId=getIntent().getIntExtra("resumeId",0);
        speciality= (ResumeBean.Speciality) getIntent().getSerializableExtra("speciality");
    }


    @OnClick({R.id.tv_master, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_master:
                 new SelectMasterView(this,tvMaster).show();
                break;
            case R.id.tv_add:
                final String language=etLanguage.getText().toString().trim();
                final String master=tvMaster.getText().toString().trim();
                if(TextUtils.isEmpty(language)){
                    ToastUtil.showLong("请输入技能/语言");
                    return;
                }
                if(TextUtils.isEmpty(master)){
                    ToastUtil.showLong("请选择掌握程度");
                    return;
                }
                AddSpecialtyP addSpecialtyP=new AddSpecialtyP();
                addSpecialtyP.setId(resumeId);
                List<AddSpecialtyP.DataBean> list=new ArrayList<>();
                list.add(new AddSpecialtyP.DataBean(language,master));
                addSpecialtyP.setSpeciality(list);

                LogUtils.e("++++++++++"+JsonUtil.objectToString(addSpecialtyP));
                //新增或编辑简历信息(简历特长)
                myResumeP.saveOrUpdateSpeciality(addSpecialtyP);
                break;
            case R.id.rel:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess() {

    }
}
