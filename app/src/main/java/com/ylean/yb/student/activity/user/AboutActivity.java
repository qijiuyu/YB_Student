package com.ylean.yb.student.activity.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.bean.AboutBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_content)
    HtmlTextView tvContent;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_wx)
    TextView tvWx;
    @BindView(R.id.tv_url)
    TextView tvUrl;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("关于我们");
        getAbout();
    }

    @OnClick({R.id.lin_back, R.id.tv_phone, R.id.tv_url, R.id.rel_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_phone:
                break;
            case R.id.tv_url:
                break;
            case R.id.rel_feedback:
                setClass(FeedBackActivity.class);
                break;
            default:
                break;
        }
    }


    /**
     * 关于我们
     */
    public void getAbout(){
        DialogUtil.showProgress(this,"数据加载中");
        HttpMethod.getAbout(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {

                final AboutBean aboutBean= (AboutBean) object;
                if(aboutBean.isSussess()){
                    if(!TextUtils.isEmpty(aboutBean.getData().getContent())){
                        tvContent.setHtml(aboutBean.getData().getContent(), new HtmlHttpImageGetter(tvContent));
                    }
                }else{
                    ToastUtil.showLong(aboutBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
