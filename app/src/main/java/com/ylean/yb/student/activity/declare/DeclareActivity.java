package com.ylean.yb.student.activity.declare;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.declare.DeclareAdapter;
import com.ylean.yb.student.adapter.declare.DeclareHeadViewAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.declare.DeclareP;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.view.MeasureListView;
import java.util.List;
import butterknife.BindView;

/**
 * 批次申报
 */
public class DeclareActivity extends BaseActivity implements DeclareP.Face {

    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list_head)
    MeasureListView listHead;
    @BindView(R.id.listView)
    MeasureListView listView;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.lin_no)
    LinearLayout linNo;
    @BindView(R.id.lin_no2)
    LinearLayout linNo2;
    private DeclareP declareP = new DeclareP(this, this);

    /**
     * 加载布局
     *
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
        tvTitle.setText("批次审报");
        linBack.setVisibility(View.GONE);
        tvDes.setText(Html.fromHtml("注：未能展示出符合实际申报的批次，请从个人档案中正确维护教育经历！<font color=\"#FA4D4F\">去维护></font>"));
    }


    /**
     * 获取申报记录
     *
     * @param list
     */
    @Override
    public void getDeclareList(final List<DeclareBean.Declare> list) {
        listView.setAdapter(new DeclareAdapter(this, list));
        if(list==null || list.size()==0){
            linNo2.setVisibility(View.VISIBLE);
        }else{
            linNo2.setVisibility(View.GONE);
        }

        //进入审核详情
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,DeclareAuditActivity.class);
                intent.putExtra("declare",list.get(position));
                startActivity(intent);
            }
        });
    }


    /**
     * 学生获取可申报批次
     *
     * @param list
     */
    @Override
    public void getBatch(List<BatchBean.Batch> list) {
        listHead.setAdapter(new DeclareHeadViewAdapter(this, list));
        if(list==null || list.size()==0){
            linNo.setVisibility(View.VISIBLE);
        }else{
            linNo.setVisibility(View.GONE);
        }
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
