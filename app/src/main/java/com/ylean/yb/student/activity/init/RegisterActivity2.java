package com.ylean.yb.student.activity.init;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.init.RegisterP;
import com.ylean.yb.student.view.SelectProvince;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ProvinceCallBack;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity2 extends BaseActivity implements RegisterP.Face2 {

    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nationality)
    TextView tvNationality;
    @BindView(R.id.tv_certificate_type)
    TextView tvCertificateType;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_card_time)
    TextView tvCardTime;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.et_wx)
    EditText etWx;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_province1)
    TextView tvProvince1;
    @BindView(R.id.tv_city1)
    TextView tvCity1;
    @BindView(R.id.tv_area1)
    TextView tvArea1;
    @BindView(R.id.et_address1)
    EditText etAddress1;
    @BindView(R.id.et_parent_mobile)
    EditText etParentMobile;
    @BindView(R.id.et_land_mobile)
    EditText etLandMobile;
    //用户对象
    private Register userInfo;
    private RegisterP registerP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register2;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        registerP=new RegisterP(this,this);

        userInfo= (Register) getIntent().getSerializableExtra("userInfo");

        if(userInfo!=null){
            tvUserName.setText(userInfo.getData().getName());
            tvSex.setText(userInfo.getData().getSex());
            tvNationality.setText(userInfo.getData().getNationality());
            tvBirthday.setText(userInfo.getData().getBirthday());
            tvNational.setText(userInfo.getData().getNation());
            tvCard.setText(userInfo.getData().getIdnum());
            tvCardTime.setText(userInfo.getData().getValiditystarttime()+"-"+userInfo.getData().getValidityendtime());
        }
    }


    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_area, R.id.tv_province1, R.id.tv_city1, R.id.tv_area1,R.id.tv_submit})
    public void onViewClicked(View view) {
        final String province=tvProvince.getText().toString().trim();
        final String city=tvCity.getText().toString().trim();
        final String area=tvArea.getText().toString().trim();
        final String province1=tvProvince1.getText().toString().trim();
        final String city1=tvCity1.getText().toString().trim();
        final String area1=tvArea1.getText().toString().trim();
        switch (view.getId()) {
            //选择省
            case R.id.tv_province:
                new SelectProvince(this, 0, null, new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvProvince.setText(listBean.getName());
                        tvProvince.setTag(listBean.getCode());
                        tvCity.setText(null);
                        tvArea.setText(null);
                    }
                }).show();
                break;
            //选择市
            case R.id.tv_city:
                if(TextUtils.isEmpty(province)){
                    ToastUtil.showLong("请先选择省");
                    return;
                }
                new SelectProvince(this, 1, (String) tvProvince.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvCity.setText(listBean.getName());
                        tvCity.setTag(listBean.getCode());
                        tvArea.setText(null);
                    }
                }).show();
                break;
            //选择区
            case R.id.tv_area:
                if(TextUtils.isEmpty(city)){
                    ToastUtil.showLong("请先选择市");
                    return;
                }
                new SelectProvince(this, 2, (String) tvCity.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvArea.setText(listBean.getName());
                        tvArea.setTag(listBean.getCode());
                    }
                }).show();
                break;
            //选择省
            case R.id.tv_province1:
                new SelectProvince(this, 0, null, new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvProvince1.setText(listBean.getName());
                        tvProvince1.setTag(listBean.getCode());
                        tvCity1.setText(null);
                        tvArea1.setText(null);
                    }
                }).show();
                break;
            //选择市
            case R.id.tv_city1:
                if(TextUtils.isEmpty(province1)){
                    ToastUtil.showLong("请先选择省");
                    return;
                }
                new SelectProvince(this, 1, (String) tvProvince1.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvCity1.setText(listBean.getName());
                        tvCity1.setTag(listBean.getCode());
                        tvArea1.setText(null);
                    }
                }).show();
                break;
            //选择区
            case R.id.tv_area1:
                if(TextUtils.isEmpty(city1)){
                    ToastUtil.showLong("请先选择市");
                    return;
                }
                new SelectProvince(this, 2, (String) tvCity1.getTag(), new ProvinceCallBack() {
                    public void onSuccess(ProvinceBean.ListBean listBean) {
                        tvArea1.setText(listBean.getName());
                        tvArea1.setTag(listBean.getCode());
                    }
                }).show();
                break;
            case R.id.tv_submit:
                final String qq=etQq.getText().toString().trim();
                final String wx=etWx.getText().toString().trim();
                final String address=etAddress.getText().toString().trim();
                final String address1=etAddress1.getText().toString().trim();
                final String mobile=etParentMobile.getText().toString().trim();
                final String landMobile=etLandMobile.getText().toString().trim();
                if(TextUtils.isEmpty(qq)){
                    ToastUtil.showLong("请输入qq号");
                    return;
                }
                if(TextUtils.isEmpty(wx)){
                    ToastUtil.showLong("请输入微信号");
                    return;
                }
                if(TextUtils.isEmpty(area)){
                    ToastUtil.showLong("请选择户口所在地");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    ToastUtil.showLong("请输入户口详细地址");
                    return;
                }
                if(TextUtils.isEmpty(area1)){
                    ToastUtil.showLong("请选择家庭地址");
                    return;
                }
                if(TextUtils.isEmpty(address1)){
                    ToastUtil.showLong("请输入家庭详细地址");
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    ToastUtil.showLong("请输入家长手机号");
                    return;
                }
                if(TextUtils.isEmpty(landMobile)){
                    ToastUtil.showLong("请输入家长座机号");
                    return;
                }
                Address addressBean=new Address();
                addressBean.setPcode((String)tvProvince.getTag());
                addressBean.setPname(province);
                addressBean.setCcode((String)tvCity.getTag());
                addressBean.setCname(city);
                addressBean.setAcode((String)tvArea.getTag());
                addressBean.setAname(area);
                addressBean.setAddress(address);

                Address addressBean1=new Address();
                addressBean1.setPcode((String)tvProvince1.getTag());
                addressBean1.setPname(province1);
                addressBean1.setCcode((String)tvCity1.getTag());
                addressBean1.setCname(city1);
                addressBean1.setAcode((String)tvArea1.getTag());
                addressBean1.setAname(area1);
                addressBean1.setAddress(address1);

                //注册第二步
                registerP.register2(mobile, JsonUtil.objectToString(addressBean),qq,JsonUtil.objectToString(addressBean1),landMobile,userInfo.getData().getId(),wx);
                break;
            default:
                break;
        }
    }

    /**
     * 数据提交成功
     */
    @Override
    public void onSuccess() {
        Intent intent=new Intent(this,BindingEmailActivity.class);
        intent.putExtra("userInfo",userInfo);
        startActivityForResult(intent,1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            setResult(1000,new Intent());
            finish();
        }
    }
}
