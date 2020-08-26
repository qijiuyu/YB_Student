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

import java.util.ArrayList;
import java.util.List;

/**
 * 选择掌握程度
 */
public class SelectMasterView extends Dialog implements View.OnClickListener {

    private Activity context;
    private CycleWheelView wheel;
    private List<String> listName=new ArrayList<String>(){{add("初级");add("熟练");add("精通");add("达人");}};
    private TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.min_wheel_select);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
        initListener();
    }

    public SelectMasterView(Activity context, TextView textView) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.textView=textView;
    }

    private void initView() {
        wheel=findViewById(R.id.wheel);
        wheel.setLabels(listName);
        wheel.setSelection(0);
        try {
            wheel.setWheelSize(3);
        } catch (CycleWheelView.CycleWheelViewException e) {
            e.printStackTrace();
        }
        wheel.setCycleEnable(false);
        wheel.setAlphaGradual(0.5f);
        wheel.setDivider(Color.parseColor("#abcdef"),1);
        wheel.setSolid(Color.WHITE,Color.WHITE);
        wheel.setLabelColor(Color.GRAY);
        wheel.setLabelSelectColor(Color.BLACK);
    }

    private void initListener() {
        findViewById(R.id.tv_cancle).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                 textView.setText(wheel.getSelectLabel());
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
