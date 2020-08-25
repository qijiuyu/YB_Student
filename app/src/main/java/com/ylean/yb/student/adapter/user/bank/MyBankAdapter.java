package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.CollMoneyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBankAdapter extends BaseAdapter {

    private Activity activity;
    private List<CollMoneyBean.CollMoney> list;

    public MyBankAdapter(Activity activity,List<CollMoneyBean.CollMoney> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_mybank, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final CollMoneyBean.CollMoney collMoney=list.get(position);
        holder.tvTitle.setText(collMoney.getBname());
        holder.tvTime.setText(collMoney.getYears()+"年");
        holder.tvMoney.setText("¥"+collMoney.getMoney()+"年");
        holder.tvSend.setText("¥"+collMoney.getYmoney()+"/"+collMoney.getYcount()+"次");
        holder.tvGetMoney.setText("¥"+collMoney.getDmoney()+"/"+collMoney.getDcount()+"次");
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_send)
        TextView tvSend;
        @BindView(R.id.tv_get_money)
        TextView tvGetMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
