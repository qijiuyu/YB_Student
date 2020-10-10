package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.IssueRecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoneyIssueAdapter extends BaseAdapter {

    private Activity activity;
    private List<IssueRecordBean.ListBean> list;

    public MoneyIssueAdapter(Activity activity, List<IssueRecordBean.ListBean> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_money_issue, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final IssueRecordBean.ListBean listBean = list.get(position);
        holder.tvReasonTxt.setVisibility(View.GONE);
        holder.tvReason.setVisibility(View.GONE);
        switch (listBean.getStatus()) {
            case 2:
                holder.tvStatus.setText("发放中");
                break;
            case 3:
                holder.tvStatus.setText("已发放");
                break;
            case 4:
                holder.tvStatus.setText("银行发放失败");
                holder.tvReasonTxt.setVisibility(View.VISIBLE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.tvReason.setText(listBean.getReason());
                break;
            case 5:
                holder.tvStatus.setText("卡号错误发放失败");
                holder.tvReasonTxt.setVisibility(View.VISIBLE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.tvReason.setText(listBean.getReason());
                break;
            case 6:
                holder.tvStatus.setText("暂停发放");
                break;
            case 8:
                holder.tvStatus.setText("补发");
                break;
            default:
                break;
        }
        holder.tvSchool.setText(listBean.getSname());
        holder.tvBankCode.setText(listBean.getBknum());
        holder.tvSendDes.setText(listBean.getBname());
        holder.tvSendTime.setText(listBean.getCreatetime());
        holder.tvMoney.setText(listBean.getMoney() + "元");
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_school)
        TextView tvSchool;
        @BindView(R.id.tv_bank_code)
        TextView tvBankCode;
        @BindView(R.id.tv_send_des)
        TextView tvSendDes;
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_reason_txt)
        TextView tvReasonTxt;
        @BindView(R.id.tv_reason)
        TextView tvReason;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
