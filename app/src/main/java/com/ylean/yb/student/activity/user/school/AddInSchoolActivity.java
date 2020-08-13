package com.ylean.yb.student.activity.user.school;

import android.content.Intent;
import android.os.Bundle;
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
import com.ylean.yb.student.utils.SelectPhotoUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在校情况说明
 */
public class AddInSchoolActivity extends BaseActivity {
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
    /**
     * 1：成绩单
     * 2：在校情况
     */
    private int imgType;

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
    }

    @OnClick({R.id.lin_back, R.id.tv_status, R.id.img_results, R.id.img_inSchool, R.id.img_template, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_status:
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
}
