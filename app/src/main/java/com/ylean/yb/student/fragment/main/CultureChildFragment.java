package com.ylean.yb.student.fragment.main;

import android.webkit.WebView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.CultrueP;
import com.zxdc.utils.library.bean.NewsDetailsBean;

import butterknife.BindView;

/**
 * 机构文化子页面
 */
public class CultureChildFragment extends BaseFragment implements CultrueP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;
    //id
    private int id;

    private CultrueP cultrueP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_culture_child;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        cultrueP=new CultrueP(activity);
        cultrueP.setFace2(this);

        //初始化webview
        initWebView(webview);

        //获取网站新闻详细
        if(isVisibleToUser && view!=null){
            cultrueP.getNewsDetails(id);
        }
    }


    /**
     * 获取id
     * @param id
     */
    public void setId(int id){
        this.id=id;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        //获取网站新闻详细
        if(isVisibleToUser && view!=null){
            cultrueP.getNewsDetails(id);
        }
    }

    /**
     * 获取网站新闻详细
     * @param detailsBean
     */
    @Override
    public void getNewsDetails(NewsDetailsBean.DetailsBean detailsBean) {
        if(detailsBean==null){
            return;
        }
        tvTitle.setText(detailsBean.getTitle());
        webview.loadDataWithBaseURL(null, detailsBean.getContent(), "text/html", "utf-8", null);
    }
}
