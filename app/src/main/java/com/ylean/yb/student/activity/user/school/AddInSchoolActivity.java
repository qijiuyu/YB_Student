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
import com.ylean.yb.student.activity.UploadFileActivity;
import com.ylean.yb.student.activity.declare.ApplySuccessActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.enumer.ApplyEnum;
import com.ylean.yb.student.persenter.UploadFileP;
import com.ylean.yb.student.persenter.user.InSchoolP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.ylean.yb.student.view.SelectInSchoolStatusView;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.InSchoolDetailsBean;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在校情况说明
 */
public class AddInSchoolActivity extends BaseActivity implements InSchoolP.Face2, UploadFileP.Face {
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
    private String schoolreport,descriptionfile;

    private UploadFileP uploadFileP=new UploadFileP(this,this);
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
        if(inSchool==null){
            return;
        }
        tvName.setText(inSchool.getName());
        tvContent.setText(inSchool.getContent());

        /**
         * 状态为4，表示要重新提交
         */
//        if(inSchool.getCheckstatus()==4){
            inSchoolP.getInSchoolDetails(inSchool.getApplyid());
//        }
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
            //获取在校情况说明模板
            case R.id.img_template:
                inSchoolP.getSchoolTemplete();
                break;
            case R.id.tv_submit:
                final String status=tvStatus.getText().toString().trim();
                final String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(status)){
                    ToastUtil.showLong("请选择当前状态");
                    return;
                }
                if(TextUtils.isEmpty(schoolreport)){
                    ToastUtil.showLong("请选择成绩单");
                    return;
                }
                if(TextUtils.isEmpty(descriptionfile)){
                    ToastUtil.showLong("请选择在校情况说明文件");
                    return;
                }
                if(TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("请输入说明信息");
                    return;
                }
                if(inSchool.getCheckstatus()==4){
                    inSchoolP.updateInSchool(inSchool.getApplyid(),(int)tvStatus.getTag(),schoolreport,descriptionfile,remark);
                }else{
                    inSchoolP.addInSchool((int)tvStatus.getTag(),schoolreport,descriptionfile,remark,inSchool.getRuleid());
                }
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
                    upload(tempFile);
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                upload(new File(list.get(0).getCompressPath()));
                break;
            default:
                break;
        }
    }


    /**
     * 上传图片
     * @param file
     */
    private void upload(File file){
        List<FileBean> list=new ArrayList<>();
        list.add(new FileBean(file.getName(),file));
        uploadFileP.uploadFile(6,list);
    }


    /**
     * 图片上传成功
     * @param imgs
     */
    @Override
    public void uploadSuccess(String[] imgs) {
        if(imgs!=null && imgs.length>0){
            if(imgType==1){
                schoolreport=imgs[0];
                Glide.with(this).load(schoolreport).into(imgResults);
            }else{
                descriptionfile=imgs[0];
                Glide.with(this).load(descriptionfile).into(imgInSchool);
            }
        }
    }


    /**
     * 查看在校情况提交的详情
     * @param detailsBean
     */
    @Override
    public void getInSchoolDetails(InSchoolDetailsBean.DetailsBean detailsBean) {
        if(detailsBean==null){
            return;
        }
        switch (detailsBean.getStatus()){
            case 1:
                 tvStatus.setText("在校");
                 break;
            case 2:
                tvStatus.setText("违纪");
                break;
            case 3:
                tvStatus.setText("休学");
                break;
            case 4:
                tvStatus.setText("参军");
                break;
            case 5:
                tvStatus.setText("退学");
                break;
            case 6:
                tvStatus.setText("死亡");
                break;
            case 7:
                tvStatus.setText("其他");
                break;
            default:
                break;
        }
        tvStatus.setTag(detailsBean.getStatus());
        //成绩单
        schoolreport=detailsBean.getSchoolreport();
        Glide.with(this).load(schoolreport).into(imgResults);
        //说明文件
        descriptionfile=detailsBean.getDescriptionfile();
        Glide.with(this).load(descriptionfile).into(imgInSchool);

        etRemark.setText(detailsBean.getContent());
    }


    /**
     * 获取在校情况说明模板
     * @param url
     */
    @Override
    public void getSchoolTemplete(String url) {
        if(TextUtils.isEmpty(url)){
            return;
        }
        Intent intent=new Intent(this,UploadFileActivity.class);
        intent.putExtra("fileUrl",url);
        startActivity(intent);
    }


    /**
     * 更新成功
     */
    @Override
    public void updateSuccess() {
        Intent intent=new Intent(this,ApplySuccessActivity.class);
        intent.putExtra("applyEnum", ApplyEnum.在校情况);
        startActivity(intent);

        //刷新上个页面的列表
        setResult(1000,new Intent());
        finish();
    }

}
