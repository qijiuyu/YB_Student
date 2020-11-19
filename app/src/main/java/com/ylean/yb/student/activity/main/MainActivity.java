package com.ylean.yb.student.activity.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.TabPagerAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.fragment.main.CooperationFragment;
import com.ylean.yb.student.fragment.main.CultrueFragment;
import com.ylean.yb.student.fragment.main.DonationFragment;
import com.ylean.yb.student.fragment.main.MainFragment;
import com.ylean.yb.student.fragment.main.YanBaoFragment;
import com.ylean.yb.student.view.PagerSlidingTabStrip;
import com.zxdc.utils.library.util.AESUtil;
import com.zxdc.utils.library.util.LogUtils;

import butterknife.BindView;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab)
    PagerSlidingTabStrip tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private final String[] titles=new String[]{"首页","机构文化","捐赠项目","交流合作","燕宝人"};
    private final Fragment[] fragments=new Fragment[]{new MainFragment(),new CultrueFragment(),new DonationFragment(),new CooperationFragment(),new YanBaoFragment()};

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        tab.setViewPager(viewPager);
        // 设置Tab是自动填充满屏幕的
        tab.setShouldExpand(true);
        tab.setSelectedTextColorResource(R.color.color_FA4D4F);
    }


    public void updatePager(int position){
        viewPager.setCurrentItem(position);
    }
}
