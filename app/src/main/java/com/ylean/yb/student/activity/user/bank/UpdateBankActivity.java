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
import com.ylean.yb.student.activity.declare.ApplySuccessActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.enumer.ApplyEnum;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.io.File;
import java.util.ArrayList;
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
    /**
     * 1：银行卡图片
     * 2：变更情况图片
     */
    private int imgType;
    private File fileBank,fileBill;

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
                final String bankCode=etBankCode.getText().toString().trim();
                if(TextUtils.isEmpty(bankCode)){
                    ToastUtil.showLong("请输入银行卡卡号");
                    return;
                }
                if(fileBank==null){
                    ToastUtil.showLong("请选择新银行卡照片");
                    return;
                }
                if(fileBill==null){
                    ToastUtil.showLong("请选择变更申请单照片");
                    return;
                }
                //变更银行卡
                updateBank(bankCode);
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
                    if(imgType==1){
                        fileBank= new File(SelectPhotoUtil.pai);
                        Glide.with(this).load(fileBank).into(imgBank);
                    }else {
                        fileBill= new File(SelectPhotoUtil.pai);
                        Glide.with(this).load(fileBill).into(imgApply);
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
                    fileBank=new File(list.get(0).getCompressPath());
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgBank);
                }else {
                    fileBill=new File(list.get(0).getCompressPath());
                    Glide.with(this).load(list.get(0).getCompressPath()).into(imgApply);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 变更银行卡
     */
    private void updateBank(String bankCode){
        DialogUtil.showProgress(this,"数据提交中");
        List<FileBean> list=new ArrayList<>();
        list.add(new FileBean("cardimg",fileBank));
        list.add(new FileBean("enclosure",fileBill));
        HttpMethod.updateBank(bankCode, list, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent(activity, ApplySuccessActivity.class);
                    intent.putExtra("applyEnum", ApplyEnum.银行卡变更成功);
                    startActivity(intent);
                    finish();
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
