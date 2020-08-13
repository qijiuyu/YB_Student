package com.ylean.yb.student.activity.init;

import android.view.WindowManager;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.TabActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.callback.PermissionCallBack;
import com.ylean.yb.student.utils.PermissionUtil;
import com.zxdc.utils.library.util.SPUtil;

public class WelcomeActivity extends BaseActivity {

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        return R.layout.activity_wellcome;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        PermissionUtil.getPermission(this, new PermissionCallBack() {
            public void onSuccess() {
                if(SPUtil.getInstance(WelcomeActivity.this).getBoolean(SPUtil.IS_FIRST_OPEN)){
                    setClass(TabActivity.class);
                }else{
                    setClass(GuideActivity.class);
                }
                finish();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
