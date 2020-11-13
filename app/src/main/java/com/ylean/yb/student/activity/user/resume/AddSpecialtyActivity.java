package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
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
public class AddSpecialtyActivity extends BaseActivity implements MyResumeP.Face2 {
    @BindView(R.id.et_language)
    EditText etLanguage;
    @BindView(R.id.tv_master)
    TextView tvMaster;
    //简历对象
    private ResumeBean.Resume resume;
    //特长老数据集合
    private List<ResumeBean.Speciality> oldList=new ArrayList<>();
    //要编辑的位置
    private int position;

    private MyResumeP myResumeP=new MyResumeP(this);

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
        myResumeP.setFace2(this);


        //获取简历对象
        resume= (ResumeBean.Resume) getIntent().getSerializableExtra("resume");
        //获取要编辑的列表位置
        position=getIntent().getIntExtra("position",-1);

        if(!TextUtils.isEmpty(resume.getSpeciality())) {
            oldList = JsonUtil.stringToList(resume.getSpeciality(), ResumeBean.Speciality.class);
            //显示要编辑的数据
            if(position!=-1){
                etLanguage.setText(oldList.get(position).getName());
                tvMaster.setText(oldList.get(position).getLevel());
            }
        }
    }


    @OnClick({R.id.tv_master, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //选择掌握程度
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
                addSpecialtyP.setId(resume.getId());

                List<AddSpecialtyP.DataBean> list=new ArrayList<>();
                //先添加好老数据
                for (int i=0;i<oldList.size();i++){
                    list.add(new AddSpecialtyP.DataBean(oldList.get(i).getName(),oldList.get(i).getLevel()));
                }

                //判断是新增数据，还是编辑老数据
                if(position!=-1){
                    list.get(position).setName(language);
                    list.get(position).setLevel(master);
                }else{
                    list.add(new AddSpecialtyP.DataBean(language,master));
                }

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

    /**
     * 操作成功
     */
    @Override
    public void onSuccess() {
        setResult(1000,new Intent());
        finish();
    }
}
