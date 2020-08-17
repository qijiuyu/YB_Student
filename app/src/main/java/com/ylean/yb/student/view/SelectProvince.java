package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.persenter.ProvinceP;
import com.zxdc.utils.library.bean.ProvinceBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectProvince extends Dialog implements ProvinceP.Face {

    @BindView(R.id.wheel)
    CycleWheelView wheel;
    private Activity context;
    private TextView textView;
    //所有省份集合
    private List<ProvinceBean.ListBean> pList;
    //根据省编码获取市集合
    private List<ProvinceBean.ListBean> cList;
    //根据市编码获取区集合
    private List<ProvinceBean.ListBean> aList;
    /**
     * 0：省
     * 1：市
     * 2：区
     */
    private int type;
    private String code;//编码
    private ProvinceP provinceP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_select);
        // 绑定初始化ButterKnife
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public SelectProvince(Activity context, TextView textView, int type,String code) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.textView = textView;
        this.type = type;
        this.code=code;
    }

    /**
     * 初始化
     */
    private void initView() {
        provinceP=new ProvinceP(context,this);
        if(type==0){
            provinceP.getProvince();
        }else if(type==1){
            provinceP.getCityByProvince(code);
        }else{
            provinceP.getAreaByCity(code);
        }
    }


    @OnClick({R.id.tv_cancle, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                 dismiss();
                 break;
            case R.id.tv_confirm:
                 if(type==0){
                     textView.setText(pList.get(wheel.getSelection()).getName());
                     textView.setTag(pList.get(wheel.getSelection()).getCode());
                 }else if(type==1){
                     textView.setText(cList.get(wheel.getSelection()).getName());
                     textView.setTag(cList.get(wheel.getSelection()).getCode());
                 }else{
                     textView.setText(aList.get(wheel.getSelection()).getName());
                     textView.setTag(aList.get(wheel.getSelection()).getCode());
                 }
                 dismiss();
                 break;
            default:
                break;
        }
    }


    /**
     * 获取所有省集合
     * @param list
     */
    @Override
    public void getProvince(List<ProvinceBean.ListBean> list) {
        pList=list;
        showData(list);
    }


    /**
     * 根据省代码获取市集合
     */
    @Override
    public void getCityByProvince(List<ProvinceBean.ListBean> list) {
        cList=list;
        showData(list);
    }


    /**
     * 根据市代码获取区集合
     */
    @Override
    public void getAreaByCity(List<ProvinceBean.ListBean> list) {
        aList=list;
        showData(list);
    }


    /**
     * 展示列表数据
     * @param list
     */
    private void showData(List<ProvinceBean.ListBean> list){
        List<String> data = new ArrayList<>();
        for (int i=0,len=list.size();i<len;i++){
              data.add(list.get(i).getName());
        }
        wheel.setLabels(data);
        wheel.setSelection(0);
        try {
            wheel.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        wheel.setCycleEnable(false);
        wheel.setAlphaGradual(0.5f);
        wheel.setDivider(Color.parseColor("#abcdef"), 1);
        wheel.setSolid(Color.WHITE, Color.WHITE);
        wheel.setLabelColor(Color.GRAY);
        wheel.setLabelSelectColor(Color.BLACK);
    }
}
