package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.ReplyCallBack;
import com.ylean.yb.student.persenter.user.MyLeaveP;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplyView extends Dialog implements MyLeaveP.Face2 {

    @BindView(R.id.et_content)
    EditText etContent;
    //标示
    private int id;
    private Activity context;
    private MyLeaveP myLeaveP;
    private ReplyCallBack replyCallBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_reply);
        // 绑定初始化ButterKnife
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public ReplyView(Activity context,int id,ReplyCallBack replyCallBack) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.id=id;
        this.replyCallBack=replyCallBack;
    }

    /**
     * 初始化
     */
    private void initView() {
        myLeaveP=new MyLeaveP(context,this);
    }


    @OnClick({R.id.tv_submit, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                final String content=etContent.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    ToastUtil.showLong("请输入要回复的内容");
                    return;
                }
                myLeaveP.reply(id,content);
                break;
            case R.id.rel:
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 回复成功
     */
    @Override
    public void reply() {
        ToastUtil.showLong("已回复");
        dismiss();
        replyCallBack.replySuccess();
    }
}
