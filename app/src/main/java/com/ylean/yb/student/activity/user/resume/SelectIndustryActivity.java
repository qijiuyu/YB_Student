package com.ylean.yb.student.activity.user.resume;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.resume.SelectIndustryAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.DictP;
import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择行业类型
 */
public class SelectIndustryActivity extends BaseActivity implements DictP.Face {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.listView)
    ListView listView;
    //行业集合数据
    private List<DictBean.Dict> listAll=new ArrayList<>();
    //已选中的职位id
    private List<Integer> ids=new ArrayList<>();
    private DictP dictP=new DictP(this,this);

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_industry;
    }


    /**
     *
     */
    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("选择行业类型");
        tvRight.setText("完成");

        //获取设置过的行业类型
        final String content=getIntent().getStringExtra("selectIndustry");
        if(!TextUtils.isEmpty(content)){
            List<DictBean.Dict> selectDict=JsonUtil.stringToList(content,DictBean.Dict.class);
            for (int i=0;i<selectDict.size();i++){
                ids.add(selectDict.get(i).getId());
            }
        }

        //根据type获取字典集合
        dictP.getDict(1);
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_right:
                List<DictBean.Dict> list=new ArrayList<>();
                for (int i=0,len=listAll.size();i<len;i++){
                    if(listAll.get(i).getSelectId()!=0){
                        list.add(listAll.get(i));
                    }
                }
                if(list.size()==0){
                    ToastUtil.showLong("请选择行业类型");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("dict", JsonUtil.objectToString(list));
                setResult(900,intent);
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 获取行业信息
     * @param list
     */
    @Override
    public void getDict(List<DictBean.Dict> list) {
        this.listAll=list;
        for (int i=0,len=this.listAll.size();i<len;i++){
            if(ids.contains(this.listAll.get(i).getId())){
                this.listAll.get(i).setSelectId(this.listAll.get(i).getId());
            }
        }
        listView.setAdapter(new SelectIndustryAdapter(this,this.listAll));
    }
}
