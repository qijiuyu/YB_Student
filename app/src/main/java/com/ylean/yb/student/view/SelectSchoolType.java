package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import java.util.ArrayList;
import java.util.List;

public class SelectSchoolType extends Dialog implements View.OnClickListener {

    private Activity context;
    private CycleWheelView wheel;
    private EditText etRelation;
    private List<String> listName=new ArrayList<String>(){{add("高中");add("中职");add("高职");add("大学");}};
    private List<Integer> listCode=new ArrayList<Integer>(){{add(0);add(1);add(2);add(3);}};
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

    public SelectSchoolType(Activity context, SelectRelationCallBack selectRelationCallBack) {
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
                 selectRelationCallBack.onSuccess(wheel.getSelectLabel(),listCode.get(wheel.getSelection()));
                 dismiss();
                 break;
            case R.id.tv_cancle:
                 dismiss();
                 break;
             default:
                 break;
        }
    }
}
