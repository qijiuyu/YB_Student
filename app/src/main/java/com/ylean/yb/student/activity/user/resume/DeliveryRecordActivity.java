package com.ylean.yb.student.activity.user.resume;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.DeliveryRecordAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.DeliveryRecordP;
import com.zxdc.utils.library.bean.DeliveryBean;
import com.zxdc.utils.library.bean.parameter.GetDeliveryRecord;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 投递记录
 */
public class DeliveryRecordActivity extends BaseActivity implements MyRefreshLayoutListener, DeliveryRecordP.Face {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private DeliveryRecordAdapter adapter;
    //页码
    private int page=1;
    //简历id
    private int resumeId;
    //数据集合
    private List<DeliveryBean.ListBean> listAll=new ArrayList<>();

    private DeliveryRecordP deliveryRecordP=new DeliveryRecordP(this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_record;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        deliveryRecordP.setFace(this);
        tvTitle.setText("投递记录");

        resumeId=getIntent().getIntExtra("resumeId",0);

        reList.setMyRefreshLayoutListener(this);
        listView.setDivider(null);
        listView.setAdapter(adapter=new DeliveryRecordAdapter(this,listAll));

        //加载数据
        reList.startRefresh();
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 刷新
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        page=1;
        deliveryRecordP.getDeliveryRecord(new GetDeliveryRecord(page, HttpMethod.pageSize,resumeId));
    }


    /**
     * 加载更多
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        deliveryRecordP.getDeliveryRecord(new GetDeliveryRecord(page, HttpMethod.pageSize,resumeId));
    }


    /**
     * 加载数据
     * @param list
     */
    @Override
    public void getDeliveryRecord(List<DeliveryBean.ListBean> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }
    }
}
