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
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.et_nickName)
    EditText etNickName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_email)
    EditText etEmail;
    //用户信息对象
    private UserInfo userInfo;

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
        tvTitle.setText("设置");
        tvRight.setText("完成");
        userInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
        if(userInfo!=null){
            if(!TextUtils.isEmpty(userInfo.getData().getPhoto())){
                Glide.with(this).load(userInfo.getData().getPhoto()).into(imgHead);
            }
            etNickName.setText(userInfo.getData().getName());
            tvMobile.setText(userInfo.getData().getPhone());
            etEmail.setText(userInfo.getData().getEmail());
        }
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.img_head, R.id.tv_mobile, R.id.rel_pwd, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                break;
            //选择头像
            case R.id.img_head:
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.tv_mobile:
                setClass(UpdateMobileActivity1.class);
                break;
            case R.id.rel_pwd:
                setClass(UpdatePwdActivity1.class);
                break;
            case R.id.tv_login_out:
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
                    Glide.with(this).load(tempFile).into(imgHead);
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
                Glide.with(this).load(list.get(0).getCompressPath()).into(imgHead);
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
        DialogUtil.showProgress(this,"上传中");
        List<FileBean> list=new ArrayList<>();
        list.add(new FileBean("photo",file));
        HttpMethod.updatephotoimg(list, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    ToastUtil.showLong("设置成功");

                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showLong(baseBean.getDesc());
                        }
                    });
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
