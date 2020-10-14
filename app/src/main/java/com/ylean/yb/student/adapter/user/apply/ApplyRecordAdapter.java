package com.ylean.yb.student.adapter.user.apply;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.ApplyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyRecordAdapter extends BaseAdapter {

    private Activity activity;
    private List<ApplyBean.ListBean> list;

    public ApplyRecordAdapter(Activity activity,List<ApplyBean.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_apply_record, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ApplyBean.ListBean listBean=list.get(position);
        switch (listBean.getType()){
            case 0:
                 holder.tvType.setText("申请类型：财务补发");
                 break;
            case 1:
                holder.tvType.setText("申请类型：变更银行卡");
                break;
            case 2:
                holder.tvType.setText("申请类型：公益时申请");
                break;
            case 3:
                holder.tvType.setText("申请类型：返校申请");
                break;
            default:
                break;
        }
        holder.tvName.setText("所属名称："+listBean.getBname());
        holder.tvTime.setText("申请时间："+listBean.getCreatetime());
        holder.tvStatus.setText(listBean.getStatustext());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_submit)
        TextView tvSubmit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
