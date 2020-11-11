package com.ylean.yb.student.activity.user.bank;

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
import com.ylean.yb.student.activity.UploadFileActivity;
import com.ylean.yb.student.activity.declare.ApplySuccessActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.ApplyRefundP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请退还奖学金
 */
public class ApplyRefundActivity extends BaseActivity implements ApplyRefundP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.tv_send_des)
    TextView tvSendDes;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.img_bank)
    ImageView imgBank;
    @BindView(R.id.img_apply)
    ImageView imgApply;
    @BindView(R.id.et_content)
    EditText etContent;
    //图片类型
    private int imgType;

    private ApplyRefundP applyRefundP=new ApplyRefundP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("申请退还奖学金");
    }

    @OnClick({R.id.lin_back, R.id.img_bank, R.id.img_apply, R.id.img_template, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.img_bank:
                imgType=1;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_apply:
                imgType=2;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            //下载模板
            case R.id.img_template:
                applyRefundP.getReturnTemplate();
                break;
            case R.id.tv_submit:
                setClass(ApplySuccessActivity.class);
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
                        Glide.with(this).load(tempFile).into(imgBank);
                    }else {
                        Glide.with(this).load(tempFile).into(imgApply);
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
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgBank);
                }else {
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgApply);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 下载模板
     * @param url
     */
    @Override
    public void getReturnTemplate(String url) {
        if(TextUtils.isEmpty(url)){
            return;
        }
        Intent intent=new Intent(this, UploadFileActivity.class);
        intent.putExtra("fileUrl",url);
        startActivity(intent);
    }
}
