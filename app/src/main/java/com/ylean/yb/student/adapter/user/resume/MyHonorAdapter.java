package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.ResumeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHonorAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumeBean.Honor> list;

    public MyHonorAdapter(Activity activity,List<ResumeBean.Honor> list) {
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

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_resume_honor, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ResumeBean.Honor honor=list.get(position);
        holder.tvTime.setText("时间："+honor.getAcquisitionTime());
        holder.tvType.setText("奖项："+honor.getPrize());
        holder.tvLevel.setText("级别："+honor.getLevel());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_level)
        TextView tvLevel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
