package com.ylean.yb.student.activity.user.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.webview.BaseWebView;
import com.ylean.yb.student.adapter.user.MyActivityAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的活动
 */
public class MyActivity extends BaseWebView {

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
        webview.loadUrl(HttpConstant.H5+"myactivityList.html?token="+ SPUtil.getInstance(this).getString(SPUtil.TOKEN));

        webview.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("back()")){
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.clearCache(true);
    }

}
