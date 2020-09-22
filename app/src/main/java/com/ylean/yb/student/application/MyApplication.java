package com.ylean.yb.student.application;

import android.text.TextUtils;

import com.tencent.bugly.crashreport.CrashReport;
import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.SPUtil;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);

        //管理Activity
        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());

        //初始化bugly异常捕获
        initBugly();
    }


    /**
     * 初始化bugly异常捕获
     */
    private void initBugly(){
        try {
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
            CrashReport.initCrashReport(this, "82f3ccc7c0", false, strategy);
        }catch (Exception e){

        }
    }


    /**
     * 判断是否登录
     * @return
     */
    public static boolean isLogin(){
        if(TextUtils.isEmpty(SPUtil.getInstance(getContext()).getString(SPUtil.TOKEN))){
            return false;
        }
        return true;
    }
}
