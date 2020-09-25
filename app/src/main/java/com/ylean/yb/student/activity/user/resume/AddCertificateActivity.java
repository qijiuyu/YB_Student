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
import com.zxdc.utils.library.bean.parameter.ResumeCertificate;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加证书
 */
public class AddCertificateActivity extends BaseActivity implements MyResumeP.Face2 {
    @BindView(R.id.tv_time)
    TextView tvTime;
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
        return R.layout.activity_add_certificate;
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
            final ResumeBean.Certificate certificate=resume.getCertificatesList().get(position);
            tvTime.setText(certificate.getAcquisitionTime());
            etName.setText(certificate.getName());
            etMemo.setText(certificate.getRemarks());
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
                final String name=etName.getText().toString().trim();
                final String memo=etMemo.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择获得时间");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入证书名称");
                    return;
                }
                ResumeCertificate resumeCertificate=new ResumeCertificate();
                resumeCertificate.setId(resume.getId());

                List<ResumeCertificate.DataBean> list=new ArrayList<>();
                for (int i=0;i<resume.getCertificatesList().size();i++){
                     list.add(new ResumeCertificate.DataBean(resume.getCertificatesList().get(i).getAcquisitionTime(),resume.getCertificatesList().get(i).getName(),resume.getCertificatesList().get(i).getRemarks()));
                }

                //判断是新增数据，还是编辑老数据
                if(position!=-1){
                    list.get(position).setAcquisitionTime(time);
                    list.get(position).setName(name);
                    list.get(position).setRemarks(memo);
                }else{
                    list.add(new ResumeCertificate.DataBean(time,name,memo));
                }
                resumeCertificate.setCertificatesPOJOS(list);

                LogUtils.e("+++++++++++"+JsonUtil.objectToString(resumeCertificate));
                myResumeP.SaveOrUpdateCertificates(resumeCertificate);
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
