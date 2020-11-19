package com.ylean.yb.student.activity.webview;

import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ylean.yb.student.R;

import butterknife.BindView;

public class MainWebViewActivity extends BaseWebView {

    @BindView(R.id.webview)
    WebView webview;
    /**
     * 1：捐赠管理系统
     * 2：学校系统
     * 3：教育局系统
     * 4：导师系统
     * 5：企业系统
     */
    private int type;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        type=getIntent().getIntExtra("type",0);
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setBuiltInZoomControls(true);
        switch (type){
            case 1:
                 webview.loadUrl("http://yb.api.yl-mall.cn/pc/platform/login.html");
                 break;
            case 2:
                webview.loadUrl("http://yb.api.yl-mall.cn/pc/school/login.html");
                break;
            case 3:
                webview.loadUrl("http://yb.api.yl-mall.cn/pc/education/login.html");
                break;
            case 4:
                 webview.loadUrl("http://yb.api.yl-mall.cn/pc/school/login.html");
                 break;
            case 5:
                 webview.loadUrl("http://yb.api.yl-mall.cn/pc/enterprise/login.html");
                 break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();//返回上个页面
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.clearCache(true);
    }
}
