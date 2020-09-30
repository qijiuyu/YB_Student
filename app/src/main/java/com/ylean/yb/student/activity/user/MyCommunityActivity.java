package com.ylean.yb.student.activity.user;

import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.webview.BaseWebView;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.SPUtil;

import butterknife.BindView;

/**
 * 我的社团
 */
public class MyCommunityActivity extends BaseWebView {

    @BindView(R.id.webview)
    WebView webview;

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
        initWebView(webview);
        webview.loadUrl(HttpConstant.H5+"mysocietyDetaile.html?token="+ SPUtil.getInstance(this).getString(SPUtil.TOKEN));
    }


    /**
     * 退出
     */
    @JavascriptInterface
    public void back(){
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            finish();
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
        }else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.clearCache(true);
    }

}
