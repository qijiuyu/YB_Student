package com.ylean.yb.student.fragment.main;

import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.CultrueP;
import com.zxdc.utils.library.bean.NewsSingle;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;

/**
 * 燕宝人子fragment
 */
public class YanBaoChildFragment extends BaseFragment implements CultrueP.Face4 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    HtmlTextView tvContent;
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
        cultrueP.setFace4(this);

        //获取网站新闻详细
        if(isVisibleToUser && view!=null){
            cultrueP.getNewsSingle(id,204);
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
            cultrueP.getNewsSingle(id,204);
        }
    }

    /**
     * 获取网站新闻单页
     * @param single
     */
    @Override
    public void getNewsSingle(NewsSingle.Single single) {
        if(single==null){
            return;
        }
        tvTitle.setText(single.getTitle());
        tvContent.setHtml(single.getContent(), new HtmlHttpImageGetter(tvContent));
    }
}
