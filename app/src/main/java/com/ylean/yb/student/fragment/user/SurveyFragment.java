package com.ylean.yb.student.fragment.user;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.news.SurveyDetailsActivity;
import com.ylean.yb.student.adapter.user.news.SurveyAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.user.SurveyP;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import com.zxdc.utils.library.view.refresh.MyRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by Administrator on 2020/3/29.
 */
public class SurveyFragment extends BaseFragment implements MyRefreshLayoutListener, SurveyP.Face {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    //页数
    private int page = 1;
    //消息集合
    private List<SurveyBean.Survey> listAll=new ArrayList<>();
    //列表适配器
    private SurveyAdapter adapter;
    private SurveyP surveyP;

    /**
     * 加载布局
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
        surveyP=new SurveyP(activity,this);

        reList.setMyRefreshLayoutListener(this);
        listView.setAdapter(adapter=new SurveyAdapter(activity,listAll));

        //加载数据
        if(view!=null && isVisibleToUser && listAll.size()==0){
            reList.startRefresh();
        }
    }

    /**
     * 返回消息列表数据
     * @param list
     */
    @Override
    public void getSurveyList(List<SurveyBean.Survey> list) {
        reList.refreshComplete();
        reList.loadMoreComplete();
        listAll.addAll(list);
        adapter.notifyDataSetChanged();
        if(list.size()< HttpMethod.pageSize){
            reList.setIsLoadingMoreEnabled(false);
        }

        /**
         * 进入详情页面
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, SurveyDetailsActivity.class);
                intent.putExtra("survey",listAll.get(position));
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
        listAll.clear();
        page=1;
        surveyP.getSurveyList(page);
    }


    /**
     * 加载
     * @param view
     */
    @Override
    public void onLoadMore(View view) {
        page++;
        surveyP.getSurveyList(page);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //加载数据
        if(view!=null && isVisibleToUser && listAll.size()==0){
            reList.startRefresh();
        }
    }
}
