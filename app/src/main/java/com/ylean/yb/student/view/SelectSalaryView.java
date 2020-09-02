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
 * 选择工作类型
 */
public class SelectSalaryView extends Dialog implements View.OnClickListener {

    private Activity context;
    private CycleWheelView wheel;
    private List<String> listName=new ArrayList<String>(){{add("1500-2000元/月");add("2000-4000/月");add("4000-6000元/月");add("6000-8000元/月");add("8000-10000元/月");add("10000-12000元/月");}};
    private List<Integer> listCode=new ArrayList<Integer>(){{add(1);add(2);add(3);add(4);add(5);add(6);}};
    private TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_select);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
        initListener();
    }

    public SelectSalaryView(Activity context, TextView textView) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.textView=textView;
    }

    private void initView() {
        wheel=findViewById(R.id.wheel);
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
                 textView.setTag(listCode.get(wheel.getSelection()));
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
