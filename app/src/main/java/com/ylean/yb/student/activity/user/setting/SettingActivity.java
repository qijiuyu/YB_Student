package com.ylean.yb.student.activity.user.setting;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.init.LoginActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SettingP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.CircleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity implements SettingP.Face {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    //用户信息对象
    private UserInfo userInfo;

    private SettingP settingP=new SettingP(this);

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        settingP.setFace(this);
        tvTitle.setText("设置");

        userInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
        if(userInfo!=null){
            if(!TextUtils.isEmpty(userInfo.getData().getPhoto())){
                Glide.with(this).load(userInfo.getData().getPhoto()).into(imgHead);
            }
            tvNickName.setText(userInfo.getData().getName());
            tvMobile.setText(userInfo.getData().getPhone());
            tvEmail.setText(userInfo.getData().getEmail());
        }
    }

    @OnClick({R.id.lin_back,R.id.img_head, R.id.tv_mobile, R.id.rel_pwd, R.id.tv_login_out})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择头像
            case R.id.img_head:
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            //变更手机号
            case R.id.tv_mobile:
                setClass(UpdatePhoneActivity.class);
                break;
            case R.id.rel_pwd:
                setClass(UpdatePwdActivity1.class);
                break;
            case R.id.tv_login_out:
                SPUtil.getInstance(this).removeMessage(SPUtil.IS_LOGIN);
                intent.setClass(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
                    //上传用户头像
                    uploadImg(tempFile);
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                //上传用户头像
                uploadImg(new File(list.get(0).getCompressPath()));
                break;
            default:
                break;
        }
    }


    /**
     * 上传用户头像
     * @param file
     */
    private void uploadImg(File file){
        List<FileBean> list=new ArrayList<>();
        list.add(new FileBean("photo",file));
        settingP.uploadImg(list);

    }

    /**
     * 头像上传成功
     */
    @Override
    public void uploadSuccess(final String imgPath) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(activity).load(imgPath).into(imgHead);
            }
        });
    }
}
