package com.ylean.yb.student.activity.init;

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
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.init.RegisterP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.util.ToastUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity implements RegisterP.Face {
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
    private File zmFile,fmFile;
    private RegisterP registerP;

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
        registerP=new RegisterP(this,this);
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
                final String card=etCard.getText().toString().trim();
                final String mobile=etMobile.getText().toString().trim();
                final String pwd=etPwd.getText().toString().trim();
                final String code=etCode.getText().toString().trim();
                if(TextUtils.isEmpty(card)){
                    ToastUtil.showLong("请输入身份证号");
                    return;
                }
                if(card.length()<18){
                    ToastUtil.showLong("请输入正确的身份证号");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入手机号");
                    return;
                }
                if(mobile.length()<11){
                    ToastUtil.showLong("请输入正确的手机号");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.showLong("请输入密码");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.showLong("请输入验证码");
                    return;
                }
                if(zmFile==null){
                    ToastUtil.showLong("请选择身份证正面照片");
                    return;
                }
                if(fmFile==null){
                    ToastUtil.showLong("请选择身份证反面照片");
                    return;
                }

                //注册
                List<FileBean> list=new ArrayList<>();
                list.add(new FileBean("positive",zmFile));
                list.add(new FileBean("back",fmFile));
                registerP.register(code,card,mobile,pwd,list);
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
                        zmFile=tempFile;
                        Glide.with(this).load(tempFile).into(imgZm);
                    }else {
                        fmFile=tempFile;
                        Glide.with(this).load(tempFile).into(imgFm);
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
                    zmFile=new File(list.get(0).getCompressPath());
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgZm);
                }else {
                    fmFile=new File(list.get(0).getCompressPath());
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgFm);
                }
                break;
            default:
                break;
        }

        if(resultCode==1000){
            finish();
        }
    }


    /**
     * 提交成功
     * @param userInfo
     */
    @Override
    public void onSuccess(Register userInfo) {
        Intent intent=new Intent(this,RegisterActivity2.class);
        intent.putExtra("userInfo",userInfo);
        startActivityForResult(intent,1000);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showLong("注册成功");
            }
        });
    }
}
