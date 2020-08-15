package com.ylean.yb.student.activity.user.news;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.TabPagerAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.fragment.user.AuditFragment;
import com.ylean.yb.student.fragment.user.MoneyFragment;
import com.ylean.yb.student.fragment.user.NoticeFragment;
import com.ylean.yb.student.fragment.user.SurveyFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息
 */
public class NewsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_audit)
    TextView tvAudit;
    @BindView(R.id.tv_money_issue)
    TextView tvMoneyIssue;
    @BindView(R.id.tv_survey)
    TextView tvSurvey;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<TextView> tvList;
    private Fragment[] fragments=new Fragment[]{new NoticeFragment(),new AuditFragment(),new MoneyFragment(),new SurveyFragment()};

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("消息");
        tvList=new ArrayList<TextView>(){{add(tvNotice);add(tvAudit);add(tvMoneyIssue);add(tvSurvey);}};

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),fragments,null));
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                updateTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_notice, R.id.tv_audit, R.id.tv_money_issue, R.id.tv_survey})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_notice:
                 updateTab(0);
                break;
            case R.id.tv_audit:
                updateTab(1);
                break;
            case R.id.tv_money_issue:
                updateTab(2);
                break;
            case R.id.tv_survey:
                updateTab(3);
                break;
        }
    }


    /**
     * 切换菜单颜色
     * @param index
     */
    private void updateTab(int index){
        for (int i=0;i<tvList.size();i++){
             if(i==index){
                 tvList.get(i).setTextColor(getResources().getColor(R.color.color_FA4D4F));
             }else{
                 tvList.get(i).setTextColor(getResources().getColor(R.color.color_666666));
             }
        }
        viewPager.setCurrentItem(index);
    }
}
