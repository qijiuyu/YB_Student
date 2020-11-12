package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.bank.ApplyRefundActivity;
import com.ylean.yb.student.activity.user.bank.ApplyReissueActivity;
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
        holder.tvPlay.setVisibility(View.GONE);
        switch (listBean.getStatus()) {
            case 2:
                holder.tvStatus.setText("发放中");
                break;
            case 3:
                holder.tvStatus.setText("已发放");
                holder.tvPlay.setVisibility(View.VISIBLE);
                holder.tvPlay.setText("申请退还奖学金");
                break;
            case 4:
                holder.tvStatus.setText("银行发放失败");
                holder.tvReasonTxt.setVisibility(View.VISIBLE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.tvReason.setText(listBean.getReason());

                holder.tvPlay.setVisibility(View.VISIBLE);
                holder.tvPlay.setText("申请补发");
                break;
            case 5:
                holder.tvStatus.setText("卡号错误发放失败");
                holder.tvReasonTxt.setVisibility(View.VISIBLE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.tvReason.setText(listBean.getReason());

                holder.tvPlay.setVisibility(View.VISIBLE);
                holder.tvPlay.setText("申请补发");
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
        holder.tvSendTime.setText(listBean.getCreatetime());
        holder.tvMoney.setText(listBean.getMoney() + "元");


        /**
         * 申请补发，或者申请退还奖学金
         */
        holder.tvPlay.setTag(listBean);
        holder.tvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final IssueRecordBean.ListBean listBean= (IssueRecordBean.ListBean) v.getTag();
                Intent intent=new Intent();
                if(listBean.getStatus()==3){
                    intent.setClass(activity, ApplyRefundActivity.class);
                }else{
                    intent.setClass(activity, ApplyReissueActivity.class);
                }
                intent.putExtra("listBean",listBean);
                activity.startActivityForResult(intent,1000);
            }
        });
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
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_reason_txt)
        TextView tvReasonTxt;
        @BindView(R.id.tv_reason)
        TextView tvReason;
        @BindView(R.id.tv_play)
        TextView tvPlay;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
