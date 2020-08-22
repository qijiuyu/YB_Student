package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.Times;
import com.zxdc.utils.library.util.JsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPositionAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumeBean.Position> list;

    public MyPositionAdapter(Activity activity,List<ResumeBean.Position> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_resume_position, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ResumeBean.Position po=list.get(position);
        if(!TextUtils.isEmpty(po.getTimes())){
            final Times times= (Times) JsonUtil.stringToObject(po.getTimes(),Times.class);
            holder.tvTime.setText("时间："+times.getStartTime()+"-"+times.getEndTime());
        }
        holder.tvName.setText("职务名称："+po.getName());
        holder.tvMemo.setText("职务描述："+po.getDescription());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_memo)
        TextView tvMemo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
