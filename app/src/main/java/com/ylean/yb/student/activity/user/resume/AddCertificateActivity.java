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
import com.zxdc.utils.library.bean.ResumePostion;
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
    //简历id
    private int resumeId;
    //要编辑的证书对象
    private ResumeBean.Certificate certificate;

    private MyResumeP myResumeP=new MyResumeP(this,this);

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
        resumeId=getIntent().getIntExtra("resumeId",0);
        certificate= (ResumeBean.Certificate) getIntent().getSerializableExtra("certificate");
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
                List<ResumeCertificate.DataBean> list=new ArrayList<>();
                ResumeCertificate.DataBean dataBean=new ResumeCertificate.DataBean();
                dataBean.setAcquisitionTime(time);
                dataBean.setName(name);
                dataBean.setRemarks(memo);
//                dataBean.setResumeId(resumeId);
                list.add(dataBean);
                resumeCertificate.setId(resumeId);
                resumeCertificate.setCertificatesPOJOS(list);

                LogUtils.e("+++++++++++"+JsonUtil.objectToString(resumeCertificate));
                myResumeP.SaveOrUpdateCertificates(JsonUtil.objectToString(resumeCertificate));
                break;
            case R.id.rel:
                 finish();
                 break;
            default:
                break;
        }
    }

}
