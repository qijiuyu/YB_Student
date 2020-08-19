package com.ylean.yb.student.activity.user.leave;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.leave.MyLeaveAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.MyLeaveP;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的留言
 */
public class MyLeaveActivity extends BaseActivity implements MyRefreshLayoutListener, MyLeaveP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页数
    private int page = 1;
    private List<LeaveBean.Leave> listAll=new ArrayList<>();
    private MyLeaveAdapter adapter;
    private MyLeaveP myLeaveP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_leave;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的留言");
        myLeaveP=new MyLeaveP(this,this);

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(adapter=new MyLeaveAdapter(this,listAll));

        //加载数据
        reList.startRefresh();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 加载数据
     * @param list
     */
    @Override
    public void getMyLeave(List<LeaveBean.Leave> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,LeaveDetailsActivity.class);
                intent.putExtra("leave",listAll.get(position));
                startActivity(intent);
            }
        });
    }


    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        myLeaveP.getMyLeave(page);
    }


    /**
     * 加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        myLeaveP.getMyLeave(page);
    }
}
