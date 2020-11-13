package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.persenter.user.MyResumeP;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.parameter.AddSchoolHonor;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加在校荣誉
 */
public class AddSchoolHonorActivity extends BaseActivity implements MyResumeP.Face2 {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_award)
    EditText etAward;
    @BindView(R.id.et_level)
    EditText etLevel;
    //简历对象
    private ResumeBean.Resume resume;
    //要编辑的位置
    private int position;

    private MyResumeP myResumeP=new MyResumeP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_school_honor;
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
        //显示要编辑的数据
        if(position!=-1){
            tvTime.setText(resume.getInSchoolHonorList().get(position).getAcquisitionTime());
            etAward.setText(resume.getInSchoolHonorList().get(position).getPrize());
            etLevel.setText(resume.getInSchoolHonorList().get(position).getLevel());
        }
    }


    @OnClick({R.id.tv_time, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        tvTime.setText(time);
                    }
                });
                break;
            case R.id.tv_add:
                final String time=tvTime.getText().toString().trim();
                final String award=etAward.getText().toString().trim();
                final String level=etLevel.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择时间");
                    return;
                }
                if(TextUtils.isEmpty(award)){
                    ToastUtil.showLong("请输入奖项");
                    return;
                }
                if(TextUtils.isEmpty(level)){
                    ToastUtil.showLong("请输入级别");
                    return;
                }

                AddSchoolHonor addSchoolHonor=new AddSchoolHonor();
                addSchoolHonor.setId(resume.getId());

                List<AddSchoolHonor.ObjectBean> list=new ArrayList<>();
                for (int i=0;i<resume.getInSchoolHonorList().size();i++){
                    list.add(new AddSchoolHonor.ObjectBean(resume.getInSchoolHonorList().get(i).getAcquisitionTime(),resume.getInSchoolHonorList().get(i).getLevel(),resume.getInSchoolHonorList().get(i).getPrize()));
                }

                //判断是新增数据，还是编辑老数据
                if(position!=-1){
                    list.get(position).setAcquisitionTime(time);
                    list.get(position).setPrize(award);
                    list.get(position).setLevel(level);
                }else{
                    list.add(new AddSchoolHonor.ObjectBean(time,award,level));
                }
                addSchoolHonor.setInSchoolHonorPOJOS(list);

                LogUtils.e("+++++++++++++"+ JsonUtil.objectToString(addSchoolHonor));
                myResumeP.saveOrUpdateInSchoolHonor(addSchoolHonor);
                break;
            case R.id.rel:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 编辑成功
     */
    @Override
    public void onSuccess() {
        setResult(1000,new Intent());
        finish();
    }
}
