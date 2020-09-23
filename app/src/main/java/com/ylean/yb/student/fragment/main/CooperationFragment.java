package com.ylean.yb.student.fragment.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.MainJLAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import butterknife.BindView;
/**
 * 交流合作
 */
public class CooperationFragment extends BaseFragment {


    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.recycleview;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        listView.setLayoutManager(new GridLayoutManager(activity, 2));
        listView.setAdapter(new MainJLAdapter(activity));


    }

}
