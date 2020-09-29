package com.ylean.yb.student.adapter.user.news;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.bean.SurveyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyAdapter extends BaseAdapter {

    private Activity activity;
    private List<SurveyBean.Survey> list;

    public SurveyAdapter(Activity activity,List<SurveyBean.Survey> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    NoticeAdapter.ViewHolder holder = null;

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_news_notice, null);
            holder = new NoticeAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (NoticeAdapter.ViewHolder) view.getTag();
        }

        final SurveyBean.Survey survey=list.get(position);
        holder.tvTitle.setText(survey.getTitle());
        holder.tvTime.setText(survey.getCreatetime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
