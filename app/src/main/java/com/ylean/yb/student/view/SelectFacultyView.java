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
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.SchoolBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectFacultyView extends Dialog {

    @BindView(R.id.wheel)
    CycleWheelView wheel;
    private List<FacultyBean.Faculty> list;
    private TextView textView;
    private Activity context;

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

    public SelectFacultyView(Activity context, List<FacultyBean.Faculty> list, TextView textView) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.list=list;
        this.textView=textView;
    }

    private void initView() {
        List<String> listName=new ArrayList<>();
        for (int i=0;i<list.size();i++){
             listName.add(list.get(i).getName());
        }
        wheel.setLabels(listName);
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


    @OnClick({R.id.tv_cancle, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                 dismiss();
                break;
            case R.id.tv_confirm:
                 textView.setText(wheel.getSelectLabel());
                 textView.setTag(list.get(wheel.getSelection()).getId());
                 dismiss();
                break;
            default:
                break;
        }
    }
}
