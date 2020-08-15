package com.ylean.yb.student.activity.user.bank;

import android.content.Intent;
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
import com.ylean.yb.student.utils.SelectPhotoUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 变更页银行卡
 */
public class UpdateBankActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_bank_code)
    EditText etBankCode;
    @BindView(R.id.img_bank)
    ImageView imgBank;
    @BindView(R.id.img_apply)
    ImageView imgApply;
    //图片类型
    private int imgType;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_bank;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("银行卡申请变更");
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
            case R.id.img_template:
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
}
