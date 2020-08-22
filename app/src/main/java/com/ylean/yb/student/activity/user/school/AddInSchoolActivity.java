package com.ylean.yb.student.activity.user.school;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.declare.ApplySuccessActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.InSchoolP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.ylean.yb.student.view.SelectInSchoolStatusView;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在校情况说明
 */
public class AddInSchoolActivity extends BaseActivity implements InSchoolP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.img_results)
    ImageView imgResults;
    @BindView(R.id.img_inSchool)
    ImageView imgInSchool;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private InSchoolBean.InSchool inSchool;
    /**
     * 1：成绩单
     * 2：在校情况
     */
    private int imgType;

    private InSchoolP inSchoolP=new InSchoolP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_inschool;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("在校情况说明");

        inSchool= (InSchoolBean.InSchool) getIntent().getSerializableExtra("inSchool");
        if(inSchool!=null){
            tvName.setText(inSchool.getName());
            tvContent.setText(inSchool.getContent());
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_status, R.id.img_results, R.id.img_inSchool, R.id.img_template, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择当前状态
            case R.id.tv_status:
                 new SelectInSchoolStatusView(this,tvStatus).show();
                break;
            case R.id.img_results:
                imgType=1;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_inSchool:
                imgType=2;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_template:
                break;
            case R.id.tv_submit:
                final String status=tvStatus.getText().toString().trim();
                final String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(status)){
                    ToastUtil.showLong("请选择当前状态");
                    return;
                }
                inSchoolP.updateInSchool(inSchool.getId(),(int)tvStatus.getTag(),"","",remark);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhotoUtil.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhotoUtil.pai);
                    if(imgType==1){
                        Glide.with(this).load(tempFile).into(imgResults);
                    }else {
                        Glide.with(this).load(tempFile).into(imgInSchool);
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                if(imgType==1){
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgResults);
                }else {
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgInSchool);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 更新成功
     */
    @Override
    public void updateSuccess() {
        setClass(ApplySuccessActivity.class);
    }
}
