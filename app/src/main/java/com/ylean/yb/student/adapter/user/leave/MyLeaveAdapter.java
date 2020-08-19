package com.ylean.yb.student.adapter.user.leave;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.LeaveBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLeaveAdapter extends BaseAdapter {

    private Activity activity;
    private List<LeaveBean.Leave> list;
    public MyLeaveAdapter(Activity activity,List<LeaveBean.Leave> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_my_leave, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final LeaveBean.Leave leave=list.get(position);
        holder.tvName.setText(leave.getMessage());
        holder.tvTime.setText(leave.getCreatetime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
