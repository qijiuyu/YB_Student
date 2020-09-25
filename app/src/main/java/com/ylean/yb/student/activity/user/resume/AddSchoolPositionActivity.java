package com.ylean.yb.student.activity.user.resume;

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
import com.zxdc.utils.library.bean.parameter.AddSchoolPosition;
import com.zxdc.utils.library.bean.parameter.Time;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加校内职务
 */
public class AddSchoolPositionActivity extends BaseActivity implements MyResumeP.Face2 {
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_memo)
    EditText etMemo;
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
        return R.layout.activity_add_school_position;
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
            etName.setText(resume.getSchoolDutiesList().get(position).getName());
            etMemo.setText(resume.getSchoolDutiesList().get(position).getDescription());
            if(!TextUtils.isEmpty(resume.getSchoolDutiesList().get(position).getTimes())){
                final Time time= (Time) JsonUtil.stringToObject(resume.getSchoolDutiesList().get(position).getTimes(),Time.class);
                tvStartTime.setText(time.getStart());
                tvEndTime.setText(time.getEnd());
            }
        }
    }


    @OnClick({R.id.tv_startTime, R.id.tv_endTime, R.id.tv_add, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_startTime:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        tvStartTime.setText(time);
                    }
                });
                break;
            case R.id.tv_endTime:
                SelectTimeUtils.selectTime(this, new TimeCallBack() {
                    public void getTime(String time) {
                        final boolean isTrue=SelectTimeUtils.judgeTime(time,tvStartTime.getText().toString().trim());
                        if(isTrue){
                            tvEndTime.setText(time);
                        }else{
                            ToastUtil.showLong("结束时间不能小于开始时间");
                        }
                    }
                });
                break;
            case R.id.tv_add:
                final String startTime=tvStartTime.getText().toString().trim();
                final String endTime=tvEndTime.getText().toString().trim();
                final String name=etName.getText().toString().trim();
                final String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(startTime)){
                    ToastUtil.showLong("请选择开始时间");
                    return;
                }
                if(TextUtils.isEmpty(endTime)){
                    ToastUtil.showLong("请选择结束时间");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入职务名称");
                    return;
                }
                if(TextUtils.isEmpty(memo)){
                    ToastUtil.showLong("请输入职务描述");
                    return;
                }

                AddSchoolPosition addSchoolPosition=new AddSchoolPosition();
                addSchoolPosition.setId(resume.getId());

                List<AddSchoolPosition.ObjectBean> list=new ArrayList<>();
                for (int i=0;i<resume.getSchoolDutiesList().size();i++){
                     list.add(new AddSchoolPosition.ObjectBean(resume.getSchoolDutiesList().get(i).getName(),resume.getSchoolDutiesList().get(i).getDescription(),resume.getSchoolDutiesList().get(i).getTimes()));
                }

                //判断是新增数据，还是编辑老数据
                if(position!=-1){
                    list.get(position).setName(name);
                    list.get(position).setDescription(memo);
                    list.get(position).setTimes(JsonUtil.objectToString(new Time(startTime,endTime)));
                }else{
                    list.add(new AddSchoolPosition.ObjectBean(name,memo,JsonUtil.objectToString(new Time(startTime,endTime))));
                }
                addSchoolPosition.setSchoolDutiesPOJOS(list);

                LogUtils.e("++++++++++++"+JsonUtil.objectToString(addSchoolPosition));
                myResumeP.saveOrUpdateSchoolDuties(addSchoolPosition);
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
        finish();
    }
}
