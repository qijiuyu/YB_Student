package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectRelation extends Dialog implements View.OnClickListener {

    private Activity context;
    private CycleWheelView wheel;
    private EditText etRelation;
    private List<String> listName=new ArrayList<String>(){{add("父亲");add("母亲");add("哥哥");add("姐姐");add("弟弟");add("妹妹");add("其他");}};
    private List<Integer> listCode=new ArrayList<Integer>(){{add(1);add(2);add(3);add(4);add(5);add(6);add(7);}};
    private SelectRelationCallBack selectRelationCallBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_relation);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
        initListener();
    }

    public SelectRelation(Activity context,SelectRelationCallBack selectRelationCallBack) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.selectRelationCallBack=selectRelationCallBack;
    }

    private void initView() {
        wheel=findViewById(R.id.wheel);
        etRelation=findViewById(R.id.et_relation);
        wheel.setLabels(listName);
        wheel.setSelection(0);
        try {
            wheel.setWheelSize(5);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        wheel.setCycleEnable(false);
        wheel.setAlphaGradual(0.5f);
        wheel.setDivider(Color.parseColor("#abcdef"),1);
        wheel.setSolid(Color.WHITE,Color.WHITE);
        wheel.setLabelColor(Color.GRAY);
        wheel.setLabelSelectColor(Color.BLACK);
        wheel.setOnWheelItemSelectedListener(new CycleWheelView.WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                if(position==6){
                    etRelation.setVisibility(View.VISIBLE);
                }else{
                    etRelation.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initListener() {
        findViewById(R.id.tv_cancle).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                 String msg=wheel.getSelectLabel();
                 String relation=etRelation.getText().toString().trim();
                 if(msg.equals("其他")){
                     if(TextUtils.isEmpty(relation)){
                         ToastUtil.showLong("请输入与本人的关系");
                     }else{
                         selectRelationCallBack.onSuccess(relation,listCode.get(wheel.getSelection()));
                         dismiss();
                     }
                 }else{
                     selectRelationCallBack.onSuccess(msg,listCode.get(wheel.getSelection()));
                     dismiss();
                 }
                 break;
            case R.id.tv_cancle:
                 dismiss();
                 break;
             default:
                 break;
        }
    }
}
