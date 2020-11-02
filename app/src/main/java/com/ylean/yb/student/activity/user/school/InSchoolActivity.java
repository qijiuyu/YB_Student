package com.ylean.yb.student.activity.user.school;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.InSchoolAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.InSchoolP;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 在校情况
 */
public class InSchoolActivity extends BaseActivity implements MyRefreshLayoutListener, InSchoolP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.lin_no)
    LinearLayout linNo;
    //页数
    private int page = 1;
    private List<InSchoolBean.InSchool> listAll=new ArrayList<>();
    //列表适配器
    private InSchoolAdapter adapter;
    private InSchoolP inSchoolP;

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_in_school;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("在校情况");
        inSchoolP=new InSchoolP(this,this);

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(adapter=new InSchoolAdapter(this,listAll));

        //加载数据
        reList.startRefresh();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取在校情况数据列表
     * @param list
     */
    @Override
    public void getInSchoolList(List<InSchoolBean.InSchool> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }
        if(listAll.size()==0){
            linNo.setVisibility(View.VISIBLE);
        }else{
            linNo.setVisibility(View.GONE);
        }
    }


    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page=1;
        listAll.clear();
        inSchoolP.getInSchoolList(page);
    }


    /**
     * 加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        inSchoolP.getInSchoolList(page);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            //加载数据
            reList.startRefresh();
        }
    }
}
