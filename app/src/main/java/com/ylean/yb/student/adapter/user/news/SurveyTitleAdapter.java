package com.ylean.yb.student.adapter.user.news;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectCallBack;
import com.ylean.yb.student.persenter.FamilyP;
import com.ylean.yb.student.view.AddFamilyView;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.SurveyDetails;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.List;

public class SurveyTitleAdapter extends RecyclerView.Adapter<SurveyTitleAdapter.MyHolder> {

    private Activity activity;
    private List<SurveyDetails.Ques> list;
    //操作的对象
    private SurveyDetails.Ques playQues;
    public SurveyTitleAdapter(Activity activity,List<SurveyDetails.Ques> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_survey_title, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
        final SurveyDetails.Ques ques = list.get(i);
        holder.tvTitle.setText((i+1)+"."+ques.getTitle());
        if(ques.getType()==1){
            holder.etContent.setVisibility(View.GONE);
            holder.listView.setVisibility(View.VISIBLE);
            holder.listView.setAdapter(new SurveyRadioAdapter(activity,ques));
        }else{
            holder.etContent.setVisibility(View.VISIBLE);
            holder.listView.setVisibility(View.GONE);

            /**
             * 监听输入答案
             */
            holder.etContent.setTag(ques);
            holder.etContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        playQues= (SurveyDetails.Ques) v.getTag();
                        holder.etContent.addTextChangedListener(textWatcher);
                    }else{
                        holder.etContent.removeTextChangedListener(textWatcher);
                    }
                }
            });
        }
    }


    /**
     * 监听输入框
     */
    TextWatcher textWatcher=new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {
            String content=s.toString().trim();
            if(TextUtils.isEmpty(content)){
                return;
            }
            playQues.setEditVlue(content);
        }
    };



    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        EditText etContent;
        MeasureListView listView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
            etContent=itemView.findViewById(R.id.et_content);
            listView=itemView.findViewById(R.id.listView);
        }
    }
}

