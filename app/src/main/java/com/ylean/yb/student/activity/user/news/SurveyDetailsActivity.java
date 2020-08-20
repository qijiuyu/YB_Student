package com.ylean.yb.student.activity.user.news;

import android.widget.ListView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.news.SurveyTitleAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SurveyP;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.SurveyDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 问卷详情
 */
public class SurveyDetailsActivity extends BaseActivity implements SurveyP.Face2 {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.listView)
    ListView listView;
    //列表对象
    private SurveyBean.Survey survey;
    private SurveyP surveyP;

    /**
     * 加载数据
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_survey_details;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("问卷详情");
        surveyP = new SurveyP(this, this);

        /**
         * 查询详情
         */
        survey = (SurveyBean.Survey) getIntent().getSerializableExtra("survey");
        if(survey!=null){
            tvName.setText(survey.getName());
            surveyP.getSurveyDetails(survey.getId());
        }
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 获取详情数据
     * @param list
     */
    @Override
    public void getSurveyDetails(List<SurveyDetails.Ques> list) {
        listView.setAdapter(new SurveyTitleAdapter(this,list));
    }
}
