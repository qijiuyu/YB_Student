package com.ylean.yb.student.activity.user.news;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.user.news.SurveyTitleAdapter;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.SurveyP;
import com.zxdc.utils.library.bean.Answer;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.SurveyDetails;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.ArrayList;
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
    RecyclerView listView;
    //列表对象
    private SurveyBean.Survey survey;
    //问题列表
    private List<SurveyDetails.Ques> surveyList;
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
        if (survey != null) {
            tvName.setText(survey.getTitle());
            surveyP.getSurveyDetails(survey.getId());
        }
    }


    @OnClick({R.id.lin_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //提交答案
            case R.id.tv_submit:
                List<Answer> list=new ArrayList<>();
                boolean b=true;
                for (int i=0;i<surveyList.size();i++){
                     final SurveyDetails.Ques ques=surveyList.get(i);

                     Answer answer=new Answer();
                     answer.setQuesid(ques.getQuesid());
                     if(ques.getType()==1){
                         if(TextUtils.isEmpty(ques.getSelectValue())){
                             ToastUtil.showLong("请选择第"+(i+1)+"题的答案");
                             b=false;
                             break;
                         }
                         answer.setValue(ques.getSelectValue());
                     }else{
                         if(TextUtils.isEmpty(ques.getEditVlue())){
                             ToastUtil.showLong("请输入第"+(i+1)+"题的答案");
                             b=false;
                             break;
                         }
                         answer.setValue(ques.getEditVlue());
                     }
                    list.add(answer);
                }

                /**
                 * 答题
                 */
                if(b){
                    surveyP.solvevoucher(survey.getId(),JsonUtil.objectToString(list));
                }
                break;
            default:
                break;
        }
    }


    /**
     * 获取详情数据
     * @param list
     */
    @Override
    public void getSurveyDetails(List<SurveyDetails.Ques> list) {
        this.surveyList=list;
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(new SurveyTitleAdapter(this, surveyList));
    }


    /**
     * 答题完成
     */
    @Override
    public void solvevoucher() {
        ToastUtil.showLong("数据已提交");
        finish();
    }
}
