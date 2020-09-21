package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.Speciality;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySpecialtyAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumeBean.Speciality> list;

    public MySpecialtyAdapter(Activity activity,List<ResumeBean.Speciality> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_resume_specialty, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ResumeBean.Speciality speciality=list.get(position);
        holder.tvName.setText("技能/语言："+speciality.getName()+"               掌握程度："+speciality.getLevel());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
