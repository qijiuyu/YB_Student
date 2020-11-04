package com.ylean.yb.student.activity.main;

import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.main.CultrueP;
import com.zxdc.utils.library.bean.NewsDetailsBean;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新闻详情页面
 */
public class NewsDetailsActivity extends BaseActivity implements CultrueP.Face2 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    HtmlTextView tvContent;

    private CultrueP cultrueP=new CultrueP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_details;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        cultrueP.setFace2(this);
        tvTitle.setText("详情");

        final int id=getIntent().getIntExtra("id",0);
        //获取网站新闻详细
        cultrueP.getNewsDetails(id);
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取详情
     * @param detailsBean
     */
    @Override
    public void getNewsDetails(NewsDetailsBean.DetailsBean detailsBean) {
        if(detailsBean==null){
            return;
        }
        tvName.setText(detailsBean.getTitle());
        tvTime.setText("发布时间："+detailsBean.getCreatetime());
        tvContent.setHtml(detailsBean.getContent(), new HtmlHttpImageGetter(tvContent));
    }
}
