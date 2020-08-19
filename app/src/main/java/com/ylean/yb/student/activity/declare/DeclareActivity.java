package com.ylean.yb.student.activity.declare;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.DeclareAdapter;
import com.ylean.yb.student.adapter.declare.DeclareHeadViewAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.declare.DeclareP;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.List;
import butterknife.BindView;

/**
 * 批次申报
 */
public class DeclareActivity extends BaseActivity  implements DeclareP.Face {

    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    //头部view
    private View headView;
    private DeclareP declareP=new DeclareP(this,this);

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_declare;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
//        setClass(DeclareDetailsActivity.class);
        tvTitle.setText("批次审报");
        linBack.setVisibility(View.GONE);
        //初始化头部view
        headView= LayoutInflater.from(this).inflate(R.layout.head_declare,null);
        listView.addHeaderView(headView);
    }


    /**
     * 获取申报记录
     * @param list
     */
    @Override
    public void getDeclareList(List<DeclareBean.Declare> list) {
        listView.setAdapter(new DeclareAdapter(this,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setClass(DeclareAuditActivity.class);
            }
        });
    }


    /**
     * 学生获取可申报批次
     * @param list
     */
    @Override
    public void getBatch(List<BatchBean.Batch> list) {
        LogUtils.e(list.size()+"++++++++++++++++++++++++++size");
        final MeasureListView listView=headView.findViewById(R.id.listView);
        TextView tvDes=headView.findViewById(R.id.tv_des);
        tvDes.setText(Html.fromHtml("注：未能展示出符合实际申报的批次，请从个人档案中正确维护教育经历！<font color=\"#FA4D4F\">去维护></font>"));
        listView.setAdapter(new DeclareHeadViewAdapter(this,list));
    }


    @Override
    public void onResume() {
        super.onResume();
        //学生获取申报记录
        declareP.getDeclareList();
        //学生获取可申报批次
        declareP.getBatch();
    }
}
