package com.ylean.yb.student.fragment;

import android.view.View;
import com.ylean.yb.student.base.BaseFragment;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

/**
 * Created by Administrator on 2020/3/29.
 */
public class aa extends BaseFragment implements MyRefreshLayoutListener {

    //页数
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    public void onRefresh(View view) {

    }

    @Override
    public void onLoadMore(View view) {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
    }
}
