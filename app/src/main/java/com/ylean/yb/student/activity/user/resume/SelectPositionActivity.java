package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.SelectPositionAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SelectPositionP;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择职位
 */
public class SelectPositionActivity extends BaseActivity implements MyRefreshLayoutListener, SelectPositionP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页码
    private int page = 1;
    private SelectPositionAdapter adapter;
    private List<ResumePostion.Position> listAll = new ArrayList<>();

    private SelectPositionP selectPositionP = new SelectPositionP(this, this);

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_position;
    }


    /**
     *
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("选择求职职位");
        tvRight.setText("完成");

        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter = new SelectPositionAdapter(this, listAll));

        //加载数据
        reList.startRefresh();
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                 List<ResumePostion.Position> list=new ArrayList<>();
                 for (int i=0,len=listAll.size();i<len;i++){
                      if(listAll.get(i).getSelectId()!=0){
                          list.add(listAll.get(i));
                      }
                 }
                 if(list.size()==0){
                     ToastUtil.showLong("请选择求职职位");
                     return;
                 }
                 Intent intent=new Intent();
                 intent.putExtra("position",JsonUtil.objectToString(list));
                 setResult(800,intent);
                 finish();
                break;
            default:
                break;
        }
    }


    /**
     * 查询数据
     *
     * @param list
     */
    @Override
    public void getResumePostion(final List<ResumePostion.Position> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                reList.refreshComplete();
                reList.loadMoreComplete();
                listAll.addAll(list);
                adapter.notifyDataSetChanged();
                if (list.size() < 100) {
                    reList.setIsLoadingMoreEnabled(false);
                }
            }
        });
    }


    /**
     * 刷新
     *
     * @param view
     */
    @Override
    public void onRefresh(View view) {
        listAll.clear();
        page = 1;
        selectPositionP.getResumePostion(JsonUtil.objectToString(new PageParam(page, 100)));
    }


    /**
     * 加载
     *
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        selectPositionP.getResumePostion(JsonUtil.objectToString(new PageParam(page, 100)));
    }
}
