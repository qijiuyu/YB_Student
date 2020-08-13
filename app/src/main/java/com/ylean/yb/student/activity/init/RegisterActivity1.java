package com.ylean.yb.student.activity.init;

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
import com.ylean.yb.student.activity.user.UserInfoActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity1 extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_card)
    EditText etCard;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.img_zm)
    ImageView imgZm;
    @BindView(R.id.img_fm)
    ImageView imgFm;
    /**
     * 1：正面照片
     * 2：反面照片
     */
    private int imgType;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register1;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("注册");
    }


    @OnClick({R.id.lin_back, R.id.tv_send_code, R.id.img_zm, R.id.img_fm, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_send_code:
                break;
            //身份证正面
            case R.id.img_zm:
                imgType=1;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            //身份证反面
            case R.id.img_fm:
                imgType=2;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.tv_next:
                setClass(UserInfoActivity.class);
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
                        Glide.with(this).load(tempFile).into(imgZm);
                    }else {
                        Glide.with(this).load(tempFile).into(imgFm);
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(imgType==1){
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgZm);
                }else {
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgFm);
                }
                break;
            default:
                break;
        }
    }
}
