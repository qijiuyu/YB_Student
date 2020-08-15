package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.bank.UpdateBankActivity;
import com.zxdc.utils.library.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBankAdapter extends BaseAdapter {

    private Activity activity;

    public HistoryBankAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 2;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_history_bank, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(position==0){
            holder.view.setVisibility(View.GONE);
        }else{
            holder.view.setVisibility(View.VISIBLE);
        }

        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, UpdateBankActivity.class);
                activity.startActivity(intent);
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_head)
        CircleImageView imgHead;
        @BindView(R.id.tv_bank_title)
        TextView tvBankTitle;
        @BindView(R.id.tv_bank_name)
        TextView tvBankName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_bank_code)
        TextView tvBankCode;
        @BindView(R.id.tv_cardholder)
        TextView tvCardholder;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
        @BindView(R.id.tv_card)
        TextView tvCard;
        @BindView(R.id.tv_update)
        TextView tvUpdate;
        @BindView(R.id.view)
        View view;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
