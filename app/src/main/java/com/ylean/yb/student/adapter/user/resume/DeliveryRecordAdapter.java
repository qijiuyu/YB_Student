package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.DeliveryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryRecordAdapter extends BaseAdapter {

    private Activity activity;
    private List<DeliveryBean.ListBean> list;

    public DeliveryRecordAdapter(Activity activity,List<DeliveryBean.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_delivery_record, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final DeliveryBean.ListBean listBean=list.get(position);
        holder.tvTitle.setText(listBean.getName());
        holder.tvMoney.setText(listBean.getSalary()+"/月");
        holder.tvContent.setText(listBean.getPositionInfo());
        holder.tvAddress.setText(listBean.getPositionLocation());
        holder.tvYear.setText(listBean.getWorkYear()+"年");
        holder.tvPeople.setText(listBean.getNumPerson()+"人");
        holder.tvTime.setText("申请于："+listBean.getCreateTime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_school)
        TextView tvSchool;
        @BindView(R.id.tv_people)
        TextView tvPeople;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
