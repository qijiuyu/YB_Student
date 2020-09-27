package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.SelectPositionAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SelectPositionP;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择职位
 */
public class SelectPositionActivity extends BaseActivity implements SelectPositionP.Face {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.listView)
    ListView listView;
    private SelectPositionAdapter adapter;
    //职位集合
    private List<ResumePostion.Position> listAll = new ArrayList<>();
    //已选中的职位id
    private List<Integer> ids=new ArrayList<>();

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

        //获取设置过的职位
        final String content=getIntent().getStringExtra("selectPosition");
        if(!TextUtils.isEmpty(content)){
            List<ResumePostion.Position> selectPosition=JsonUtil.stringToList(content,ResumePostion.Position.class);
            for (int i=0;i<selectPosition.size();i++){
                 ids.add(selectPosition.get(i).getId());
            }
        }

        listView.setAdapter(adapter = new SelectPositionAdapter(this, listAll));

        //查询职位类型信息
        selectPositionP.getResumePostion();
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
        listAll.addAll(list);

        for (int i=0,len=listAll.size();i<len;i++){
            if(ids.contains(listAll.get(i).getId())){
                listAll.get(i).setSelectId(listAll.get(i).getId());
            }
        }
        adapter.notifyDataSetChanged();
    }
}
