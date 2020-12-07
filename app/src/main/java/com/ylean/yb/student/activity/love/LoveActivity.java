package com.ylean.yb.student.activity.love;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.webview.BaseWebView;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.SPUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;

/**
 * 爱心社
 */
public class LoveActivity extends BaseWebView {

    @BindView(R.id.webview)
    WebView webview;

    /**
     * 加载布局
     * @return
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
        //注册eventBus
        EventBus.getDefault().register(this);

        initWebView(webview);

        webview.loadUrl(HttpConstant.H5+"loveSociety.html?token="+ SPUtil.getInstance(this).getString(SPUtil.TOKEN));

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                if(url.startsWith(HttpConstant.H5+"loveSociety.html")){
                    EventBus.getDefault().post(new EventBusType(EventStatus.SHOW_TAB));
                }else{
                    EventBus.getDefault().post(new EventBusType(EventStatus.HIDDEN_TAB));
                }
                super.onPageFinished(view, url);
            }
        });
    }


    /**
     * EventBus注解
     */
    @Subscribe
    public void onEvent(EventBusType eventBusType) {
        if(eventBusType.getStatus()==EventStatus.BACK_H5){
            webview.goBack();//返回上个页面
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.clearCache(true);
        EventBus.getDefault().unregister(this);
    }
}
