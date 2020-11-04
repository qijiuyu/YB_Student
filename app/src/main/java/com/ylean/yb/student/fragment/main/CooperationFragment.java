package com.ylean.yb.student.fragment.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.TabPagerAdapter;
import com.ylean.yb.student.adapter.main.CultrueAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.CultrueP;
import com.zxdc.utils.library.bean.NewsTitleBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
/**
 * 交流合作
 */
public class CooperationFragment extends BaseFragment  implements CultrueP.Face {

    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //标题数组
    private String[] titles;
    //子fragment数组
    private Fragment[] fragments;
    //标题适配器
    private CultrueAdapter cultrueAdapter;
    private List<NewsTitleBean.ListBean> listAll=new ArrayList<>();

    private CultrueP cultrueP;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cultrue;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        cultrueP=new CultrueP(activity);
        cultrueP.setFace(this);

        if(isVisibleToUser && view!=null && listAll.size()==0){
            //获取网站新闻标题列表
            cultrueP.getNewsTitle(203);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser && view!=null && listAll.size()==0){
            //获取网站新闻标题列表
            cultrueP.getNewsTitle(203);
        }
    }


    /**
     * 获取网站新闻标题列表
     * @param list
     */
    @Override
    public void getNewsTitle(final List<NewsTitleBean.ListBean> list) {
        if(list==null || list.size()==0){
            return;
        }
        this.listAll.addAll(list);

        /**
         * 显示标题
         */
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listView.setLayoutManager(linearLayoutManager);
        listView.setAdapter(cultrueAdapter=new CultrueAdapter(activity, listAll, new CultrueAdapter.ClickCallBack() {
            @Override
            public void onClick(int position) {
                viewPager.setCurrentItem(position);
            }
        }));


        /**
         * 显示子fragment
         */
        titles=new String[listAll.size()];
        fragments=new Fragment[listAll.size()];
        for (int i=0;i<listAll.size();i++){
            titles[i]=listAll.get(i).getName();
            fragments[i]=new CooperationChildFragment();
        }

        //给第一个fragment设置id
        ((CooperationChildFragment)fragments[0]).setId(listAll.get(0).getId());

        final TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                ((CooperationChildFragment)fragments[position]).setId(listAll.get(position).getId());
                cultrueAdapter.setPosition(position);
                listView.scrollToPosition(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
