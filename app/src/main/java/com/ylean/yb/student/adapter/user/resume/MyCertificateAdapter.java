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

public class MyCertificateAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumeBean.Certificate> list;

    public MyCertificateAdapter(Activity activity,List<ResumeBean.Certificate> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_resume_certificate, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ResumeBean.Certificate certificate=list.get(position);
        holder.tvTime.setText("获得时间："+certificate.getAcquisitionTime());
        holder.tvName.setText("证书名称："+certificate.getName());
        holder.tvMemo.setText("成绩/证书介绍："+certificate.getRemarks());
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
