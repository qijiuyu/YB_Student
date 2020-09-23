package com.ylean.yb.student.activity.user.bank;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyBankP;
import com.zxdc.utils.library.util.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证银行卡
 */
public class ValidationActivity extends BaseActivity implements MyBankP.Face3 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.et_code)
    EditText etCode;
    private char[] arr;

    private MyBankP myBankP=new MyBankP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_validation_bank;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        myBankP.setFace3(this);
        tvTitle.setText("验证银行卡");

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                arr = editable.toString().toCharArray();
                tv1.setText(null);
                tv2.setText(null);
                tv3.setText(null);
                tv4.setText(null);
                for (int i = 0; i < arr.length; i++) {
                    if (i == 0) {
                        tv1.setText(String.valueOf(arr[0]));
                    } else if (i == 1) {
                        tv2.setText(String.valueOf(arr[1]));
                    } else if (i == 2) {
                        tv3.setText(String.valueOf(arr[2]));
                    } else if (i == 3) {
                        tv4.setText(String.valueOf(arr[3]));
                    }
                }
                if(arr.length==4){
                    //隐藏键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etCode.getWindowToken(), 0);

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < arr.length; i++) {
                        sb.append(arr[i]);
                    }
                }
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_submit:
                if(arr==null || arr.length!=4){
                    ToastUtil.showLong("请输入银行卡后四位进行验证");
                    return;
                }
                myBankP.verBank(etCode.getText().toString().trim());
                break;
            default:
                break;
        }
    }


    /**
     * 验证成功
     */
    @Override
    public void verBankSuccess() {
        finish();
    }
}
