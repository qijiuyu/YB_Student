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
import com.ylean.yb.student.activity.user.apply.ApplyRecordActivity;
import com.ylean.yb.student.activity.user.bank.ProgressActivity;
import com.ylean.yb.student.activity.user.bank.UpdateBankActivity;
import com.ylean.yb.student.activity.user.bank.ValidationActivity;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.view.CircleImageView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryBankAdapter extends BaseAdapter {

    private Activity activity;
    private List<BankBaseBean.BankBase> list;
    /**
     * true：显示历史银行卡
     * false：隐藏历史银行卡
     */
    public boolean isShowHistory=false;

    public HistoryBankAdapter(Activity activity,List<BankBaseBean.BankBase> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        if(isShowHistory){
            return list.size();
        }else{
            if(list.size()>0){
                return 1;
            }else{
                return 0;
            }
        }
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
        holder.tvCardholder.setText("持卡人姓名："+bankBase.getUname());
        holder.tvMobile.setText("手机号："+bankBase.getPhone());
        holder.tvCard.setText("身份证号："+bankBase.getIdnum());


        /**
         * 历史银行卡会有阴影遮罩
         */
        if(position==0){
            holder.view.setVisibility(View.GONE);
        }else{
            holder.view.setVisibility(View.VISIBLE);
        }

        holder.tvPlay1.setVisibility(View.GONE);
        holder.tvPlay2.setVisibility(View.GONE);
        holder.tvLookAudit.setVisibility(View.GONE);
        if(position==0){
            switch (bankBase.getBankstatus()){
                case 0:
                case 1:
                case 2:
                    holder.tvStatus.setText("未开卡");
                    break;
                case 3:
                case 8:
                    holder.tvStatus.setText("办理中");
                    holder.tvPlay1.setVisibility(View.VISIBLE);
                    holder.tvPlay1.setText("查看进度");
                    break;
                case 4:
                    holder.tvStatus.setText("待验证");
                    holder.tvPlay1.setVisibility(View.VISIBLE);
                    holder.tvPlay2.setVisibility(View.VISIBLE);
                    holder.tvPlay1.setText("查看进度");
                    break;
                case 5:
                    holder.tvStatus.setText("已通过");
                    holder.tvPlay1.setVisibility(View.VISIBLE);
                    holder.tvLookAudit.setVisibility(View.VISIBLE);
                    holder.tvPlay1.setText("变更银行卡");
                    break;
                case 6:
                    holder.tvStatus.setText("已更改");
                    break;
                case 7:
                    holder.tvStatus.setText("失败");
                    break;
                default:
                    break;
            }
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
                    case 3:
                    case 4:
                    case 8:
                         intent.setClass(activity, ProgressActivity.class);
                         break;
                    //已通过： 可以变更了
                    case 5:
                         intent.setClass(activity,UpdateBankActivity.class);
                         break;
                     default:
                         break;
                }
                activity.startActivity(intent);
            }
        });


        /**
         * 去验证
         */
        holder.tvPlay2.setTag(bankBase);
        holder.tvPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BankBaseBean.BankBase bankBase= (BankBaseBean.BankBase) v.getTag();
                Intent intent=new Intent(activity, ValidationActivity.class);
                intent.putExtra("bankCode",bankBase.getBanknum());
                activity.startActivity(intent);
            }
        });


        /**
         * 查看审核记录
         */
        holder.tvLookAudit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ApplyRecordActivity.class);
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
        @BindView(R.id.tv_look_audit)
        TextView tvLookAudit;
        @BindView(R.id.view)
        View view;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public void setIsShow(boolean isShowHistory){
        this.isShowHistory=isShowHistory;
        notifyDataSetChanged();
    }
}
