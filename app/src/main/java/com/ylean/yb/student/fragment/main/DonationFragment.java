package com.ylean.yb.student.fragment.main;

import android.widget.ListView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.JZAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import butterknife.BindView;

/**
 * 捐赠项目
 */
public class DonationFragment extends BaseFragment {


    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.listview;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();

        listView.setDivider(null);
        listView.setAdapter(new JZAdapter(activity));

    }

}
