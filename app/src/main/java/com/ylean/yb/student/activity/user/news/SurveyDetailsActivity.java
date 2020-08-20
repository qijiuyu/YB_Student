package com.ylean.yb.student.activity.user.news;

import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SurveyP;
import com.zxdc.utils.library.bean.SurveyBean;

/**
 * 问卷详情
 */
public class SurveyDetailsActivity extends BaseActivity implements SurveyP.Face2 {

    //列表对象
    private SurveyBean.Survey survey;
    private SurveyP surveyP;

    /**
     * 加载数据
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_survey_details;
    }

    @Override
    protected void initData() {
        super.initData();
        surveyP=new SurveyP(this,this);
        survey= (SurveyBean.Survey) getIntent().getSerializableExtra("survey");
        surveyP.getSurveyDetails(survey.getId());
    }
}
