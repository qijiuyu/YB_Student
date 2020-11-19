package com.ylean.yb.student.activity.user.apply;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.apply.ApplyRecordAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.ApplyP;
import com.zxdc.utils.library.bean.ApplyBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请记录
 */
public class ApplyRecordActivity extends BaseActivity implements MyRefreshLayoutListener, ApplyP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.lin_no)
    LinearLayout linNo;
    //列表适配器
    private ApplyRecordAdapter adapter;
    //页数
    private int page = 1;
    //列表总数据
    private List<ApplyBean.ListBean> listAll=new ArrayList<>();

    private ApplyP applyP=new ApplyP(this);


    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_record;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        applyP.setFace(this);
        tvTitle.setText("申请记录");

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(adapter=new ApplyRecordAdapter(this,listAll));

        //加载数据
        reList.startRefresh();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 刷新数据
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        listAll.clear();
        page=1;
        applyP.getApplyList(page);
    }


    /**
     * 加载更多数据
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        applyP.getApplyList(page);
    }


    /**
     * 获取查询的列表数据
     * @param list
     */
    @Override
    public void getApplyList(List<ApplyBean.ListBean> list) {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ApplyBean.ListBean listBean=listAll.get(position);
                Intent intent=new Intent();
                intent.putExtra("id",listBean.getId());
                switch (listBean.getType()){
                    case 0:
                        intent.setClass(activity,ReissueAuditActivity.class);
                         break;
                    case 1:
                        intent.setClass(activity,BankAuditActivity.class);
                        break;
                    case 2:
                        intent.setClass(activity,WelfareAuditActivity.class);
                        break;
                    case 3:
                        intent.setClass(activity,InSchoolAuditActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
