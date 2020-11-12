package com.ylean.yb.student.activity.declare;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.EconomicAdapter;
import com.ylean.yb.student.adapter.user.mine.FamilyAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.SelectCallBack;
import com.ylean.yb.student.enumer.ApplyEnum;
import com.ylean.yb.student.persenter.EconomicP;
import com.ylean.yb.student.persenter.FamilyP;
import com.ylean.yb.student.persenter.UploadFileP;
import com.ylean.yb.student.persenter.declare.ApplyDeclareP;
import com.ylean.yb.student.persenter.declare.DeclareP;
import com.ylean.yb.student.persenter.user.UserP;
import com.ylean.yb.student.utils.SelectPhotoUtil;
import com.ylean.yb.student.view.AddFamilyView;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MeasureListView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 批次申报
 */
public class AddDeclareActivity extends BaseActivity implements DeclareP.Face2,FamilyP.Face, EconomicP.Face, UploadFileP.Face, ApplyDeclareP.Face, UserP.Face3 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_batchNo)
    TextView tvBatchNo;
    @BindView(R.id.tv_valid_time)
    TextView tvValidTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nationality)
    TextView tvNationality;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_certificate_type)
    TextView tvCertificateType;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_card_time)
    TextView tvCardTime;
    @BindView(R.id.tv_hk_address)
    TextView tvHkAddress;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.list_family)
    RecyclerView listFamily;
    @BindView(R.id.list_economic)
    MeasureListView listEconomic;
    @BindView(R.id.tv_add_family)
    TextView tvAddFamily;
    @BindView(R.id.img_zm)
    ImageView imgZm;
    @BindView(R.id.img_fm)
    ImageView imgFm;
    @BindView(R.id.img_hk1)
    ImageView imgHk1;
    @BindView(R.id.img_hk2)
    ImageView imgHk2;
    @BindView(R.id.img_notice)
    ImageView imgNotice;
    @BindView(R.id.img_other)
    ImageView imgOther;
    //家庭成员集合
    private List<FamilyBean.ListBean> familyList = new ArrayList<>();
    //资助经济集合
    private List<EconomicBean.Economic> economicList = new ArrayList<>();
    /**
     * 1：身份证正面
     * 2：身份证反面
     * 3：户口户主
     * 4：户口本人
     * 5：通知书
     * 6：其他
     */
    private int imgType;
    //图片链接
    private String cardZ,cardF,hk1,hk2,notice,other;
    //批次id
    private int batchId;
    //申报id
    private int applyId;
    //考号
    private String num;

    private DeclareP declareP=new DeclareP(this);
    private UserP userP=new UserP(this);
    private FamilyP familyP = new FamilyP(this, this);
    private EconomicP economicP = new EconomicP(this, this);
    private UploadFileP uploadFileP=new UploadFileP(this,this);
    private ApplyDeclareP applyDeclareP=new ApplyDeclareP(this,this);

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_declare;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();

        tvTitle.setText("批次审报");
        //获取批次id
        batchId = getIntent().getIntExtra("batchId",0);
        //获取申报id
        applyId=getIntent().getIntExtra("applyId",0);
        //获取考号
        num=getIntent().getStringExtra("num");


        declareP.setFace2(this);
        //学生获取可申报批次详情
        declareP.getBatchDetailed(batchId);

        userP.setFace3(this);
        //获取学生申报或查看申报基本信息
        userP.getUserInfoByApply();

        //查询家庭成员数据
        familyP.getFamilyList();

        //获取所有资助批次经济情况
        economicP.getEconomicList();
    }


    @OnClick({R.id.lin_back, R.id.tv_add_family,R.id.img_zm, R.id.img_fm, R.id.img_hk1, R.id.img_hk2, R.id.img_notice, R.id.img_other, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //添加家庭成员
            case R.id.tv_add_family:
                new AddFamilyView(this, null, new SelectCallBack() {
                    @Override
                    public void selectBack(Object object) {
                        //查询家庭成员数据
                        familyP.getFamilyList();
                    }
                }).show();
                break;
            case R.id.img_zm:
                imgType=1;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_fm:
                imgType=2;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_hk1:
                imgType=3;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_hk2:
                imgType=4;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_notice:
                imgType=5;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.img_other:
                imgType=6;
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.tv_submit:
                StringBuilder economicId=new StringBuilder();
                for (int i=0;i<economicList.size();i++){
                     if(economicList.get(i).isSelect()){
                         economicId.append(economicList.get(i).getId()+",");
                     }
                }
                if(TextUtils.isEmpty(economicId)){
                    ToastUtil.showLong("请选择经济情况");
                    return;
                }else{
                    economicId.insert(0,"[");
                    economicId.deleteCharAt(economicId.length()-1);
                    economicId.append("]");
                }
                if(TextUtils.isEmpty(cardZ)){
                    ToastUtil.showLong("请上传身份证正面照片");
                    return;
                }
                if(TextUtils.isEmpty(cardF)){
                    ToastUtil.showLong("请上传身份证反面照片");
                    return;
                }
                if(TextUtils.isEmpty(hk1)){
                    ToastUtil.showLong("请上传户口本户主照片");
                    return;
                }
                if(TextUtils.isEmpty(hk2)){
                    ToastUtil.showLong("请上传户口本本人照片");
                    return;
                }
                if(TextUtils.isEmpty(notice)){
                    ToastUtil.showLong("请上传录取通知书照片");
                    return;
                }
                if(applyId==0){
                    applyDeclareP.applyDeclare(batchId,economicId.toString(),cardZ,cardF,hk1,hk2,notice,other,num);
                }else{
                    applyDeclareP.againdeclare(applyId,economicId.toString(),cardZ,cardF,hk1,hk2,notice,other);
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
                    //上传图片
                    upload(tempFile);
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                //上传图片
                upload(new File(list.get(0).getCompressPath()));
                break;
            default:
                break;
        }
    }


    /**
     * 返回详情
     * @param batch
     */
    @Override
    public void getBatchDetailed(BatchDetails.Batch batch) {
        if (batch != null) {
            tvBatchNo.setText(batch.getName());
            tvValidTime.setText(batch.getStarttime().split(" ")[0] + "至" + batch.getEndtime().split(" ")[0]);
        }
        scrollView.scrollTo(0,0);
    }


    /**
     * 显示用户基本信息
     */
    @Override
    public void getUserInfoByApply(UserInfo.UserBean userBean) {
        if(userBean==null){
            return;
        }
        if(!TextUtils.isEmpty(userBean.getPhoto())){
            Glide.with(this).load(userBean.getPhoto()).into(imgHead);
        }
        tvName.setText(userBean.getName());
        tvSex.setText(userBean.getSex());
        tvNationality.setText(userBean.getNationality());
        if(!TextUtils.isEmpty(userBean.getBirthday())){
            tvBirthday.setText(userBean.getBirthday().split(" ")[0]);
        }
        tvNational.setText(userBean.getNation());
        tvCard.setText(userBean.getIdnum());
        if (!TextUtils.isEmpty(userBean.getValiditystarttime()) && !TextUtils.isEmpty(userBean.getValidityendtime())) {
            tvCardTime.setText(userBean.getValiditystarttime().split(" ")[0] + "至" + userBean.getValidityendtime().split(" ")[0]);
        }
        tvEmail.setText(userBean.getEmail());
        tvMobile.setText(userBean.getPhone());
        if (!TextUtils.isEmpty(userBean.getResidenceaddress())) {
            final Address address = (Address) JsonUtil.stringToObject(userBean.getResidenceaddress(), Address.class);
            tvHkAddress.setText(address.getAddress());
        }
        scrollView.scrollTo(0,0);

    }


    /**
     * 查询家庭成员集合
     *
     * @param list
     */
    @Override
    public void getFamily(List<FamilyBean.ListBean> list) {
        this.familyList = list;
        listFamily.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listFamily.setAdapter(new FamilyAdapter(this, familyList, familyP));
    }


    /**
     * 删除家庭成员
     *
     * @param listBean
     */
    @Override
    public void deleteSuccess(FamilyBean.ListBean listBean) {
        familyList.remove(listBean);
        listFamily.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listFamily.setAdapter(new FamilyAdapter(this, familyList, familyP));
    }


    /**
     * 获取所有资助批次经济情况
     *
     * @param list
     */
    @Override
    public void getEconomicList(List<EconomicBean.Economic> list) {
        this.economicList = list;
        listEconomic.setAdapter(new EconomicAdapter(this, list));
        scrollView.scrollTo(0,0);
    }


    /**
     * 上传图片
     * @param file
     */
    private void upload(File file){
        List<FileBean> list=new ArrayList<>();
        list.add(new FileBean(file.getName(),file));
        uploadFileP.uploadFile(imgType,list);
    }


    /**
     * 图片上传成功
     * @param imgs
     */
    @Override
    public void uploadSuccess(String[] imgs) {
      if(imgs!=null && imgs.length>0){
          switch (imgType){
              case 1:
                  cardZ=imgs[0];
                  Glide.with(this).load(cardZ).into(imgZm);
                  break;
              case 2:
                  cardF=imgs[0];
                  Glide.with(this).load(cardF).into(imgFm);
                  break;
              case 3:
                  hk1=imgs[0];
                  Glide.with(this).load(hk1).into(imgHk1);
                  break;
              case 4:
                  hk2=imgs[0];
                  Glide.with(this).load(hk2).into(imgHk2);
                  break;
              case 5:
                  notice=imgs[0];
                  Glide.with(this).load(notice).into(imgNotice);
                  break;
              case 6:
                  other=imgs[0];
                  Glide.with(this).load(other).into(imgOther);
                  break;
              default:
                  break;
          }
      }
    }


    /**
     * 申报成功
     */
    @Override
    public void applySuccess() {
        Intent intent=new Intent(this,ApplySuccessActivity.class);
        intent.putExtra("applyEnum", ApplyEnum.批次申报成功);
        startActivity(intent);
        finish();
    }

}
