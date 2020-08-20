package com.ylean.yb.student.adapter.user.news;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.SurveyDetails;
import com.zxdc.utils.library.view.MeasureListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyTitleAdapter extends BaseAdapter {

    private Activity activity;
    private List<SurveyDetails.Ques> list;

    public SurveyTitleAdapter(Activity activity, List<SurveyDetails.Ques> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_survey_title, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final SurveyDetails.Ques ques = list.get(position);
        holder.tvTitle.setText((position+1)+"."+ques.getTitle());
        if(ques.getType()==1){
            holder.etContent.setVisibility(View.GONE);
            holder.listView.setVisibility(View.VISIBLE);
            holder.listView.setAdapter(new SurveyRadioAdapter(activity,ques));
        }else{
            holder.etContent.setVisibility(View.VISIBLE);
            holder.listView.setVisibility(View.GONE);
        }

        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.et_content)
        EditText etContent;
        @BindView(R.id.listView)
        MeasureListView listView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
