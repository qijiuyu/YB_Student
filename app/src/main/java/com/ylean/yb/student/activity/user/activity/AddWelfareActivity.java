package com.ylean.yb.student.activity.user.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.declare.ApplySuccessActivity;
import com.ylean.yb.student.adapter.GridViewImgAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.view.MyGridView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公益时申请
 */
public class AddWelfareActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_apply_time)
    EditText tvApplyTime;
    @BindView(R.id.et_memo)
    EditText etMemo;
    @BindView(R.id.gridView)
    MyGridView gridView;
    private GridViewImgAdapter gridViewImgAdapter;
    //选择的照片
    private List<LocalMedia> imgList=new ArrayList<>();

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_welfare;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("公益时申请");

        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==imgList.size()){
                    SelectPhotoUtil.SelectPhoto(activity,3);
                }
            }
        });
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
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
                    LocalMedia localMedia=new LocalMedia();
                    localMedia.setCompressPath(SelectPhotoUtil.pai);
                    gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
                    gridView.setAdapter(gridViewImgAdapter);
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                imgList.addAll(list);
                gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
                gridView.setAdapter(gridViewImgAdapter);
                break;
            default:
                break;
        }
    }
}
