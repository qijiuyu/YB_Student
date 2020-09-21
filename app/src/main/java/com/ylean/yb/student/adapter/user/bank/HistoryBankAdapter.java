package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.bank.ProgressActivity;
import com.ylean.yb.student.activity.user.bank.UpdateBankActivity;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBankAdapter extends BaseAdapter {

    private Activity activity;
    private List<BankBaseBean.BankBase> list;

    public HistoryBankAdapter(Activity activity,List<BankBaseBean.BankBase> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
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

        final BankBaseBean.BankBase bankBase=list.get(position);
        //显示图片
        String imgUrl = bankBase.getBankimgurl();
        if(!TextUtils.isEmpty(imgUrl)){
            holder.imgHead.setTag(R.id.imageid, imgUrl);
            if (holder.imgHead.getTag(R.id.imageid) != null && imgUrl == holder.imgHead.getTag(R.id.imageid)) {
                Glide.with(activity).load(imgUrl).into(holder.imgHead);
            }
        }

        holder.tvBankName.setText(bankBase.getBankname());
        holder.tvBankCode.setText(bankBase.getBanknum());
        switch (bankBase.getBankstatus()){
            case 0:
                 holder.tvStatus.setText("未提交");
                 holder.tvPlay1.setVisibility(View.GONE);
                 break;
            case 1:
                holder.tvStatus.setText("已提交");
                holder.tvPlay1.setVisibility(View.VISIBLE);
                holder.tvPlay1.setText("办理进度");
                break;
            case 2:
                holder.tvStatus.setText("未开卡");
                holder.tvPlay1.setVisibility(View.GONE);
                break;
            case 3:
                holder.tvStatus.setText("已开卡");
                break;
            case 4:
                holder.tvStatus.setText("已邮寄");
                break;
            case 5:
                holder.tvStatus.setText("正在使用");
                break;
            case 6:
                holder.tvStatus.setText("已更改");
                break;
            case 7:
                holder.tvStatus.setText("失败");
                break;
            case 8:
                holder.tvStatus.setText("开卡中");
                break;
            default:
                break;
        }
        holder.tvCardholder.setText("持卡人姓名："+bankBase.getUname());
        holder.tvMobile.setText("手机号："+bankBase.getPhone());
        holder.tvCard.setText("身份证号："+bankBase.getIdnum());

        if(position==0){
            holder.view.setVisibility(View.GONE);
        }else{
            holder.view.setVisibility(View.VISIBLE);
        }


        /**
         * 变更银行卡
         */
        holder.tvPlay1.setTag(bankBase);
        holder.tvPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BankBaseBean.BankBase bankBase= (BankBaseBean.BankBase) v.getTag();
                Intent intent=new Intent();
                switch (bankBase.getBankstatus()){
                    //已提交： 可以查看办理进度
                    case 1:
                         intent.setClass(activity, ProgressActivity.class);
                         break;
                    //已邮寄：去验证
                    case 4:
                         break;
                    //已更改： 可以变更了
                    case 6:
                         intent.setClass(activity,UpdateBankActivity.class);
                         break;
                     default:
                         break;
                }
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
        @BindView(R.id.tv_play1)
        TextView tvPlay1;
        @BindView(R.id.tv_play2)
        TextView tvPlay2;
        @BindView(R.id.view)
        View view;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
