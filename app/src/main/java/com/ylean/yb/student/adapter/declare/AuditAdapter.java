package com.ylean.yb.student.adapter.declare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.AuditBean;

/**
 * 捐赠
 */
public class AuditAdapter extends RecyclerView.Adapter<AuditAdapter.MyHolder> {

    private Context context;
    private AuditBean.Audit audit;
    public AuditAdapter(Context context, AuditBean.Audit audit) {
        super();
        this.context = context;
        this.audit=audit;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_declare_audit, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        if(i==0){
            holder.viewLeft.setVisibility(View.GONE);
        }else{
            holder.viewLeft.setVisibility(View.VISIBLE);
        }
        if(i==(getItemCount()-1)){
            holder.viewRitht.setVisibility(View.GONE);
        }else{
            holder.viewRitht.setVisibility(View.VISIBLE);
        }



        /**
         * 显示审核时间
         */
        if(i==0){
            holder.tvTime.setText(audit.getCreatetime());
        }else if(i==1){
            if(audit.getStatus()==1 || audit.getStatus()==2 || audit.getStatus()==3 || audit.getStatus()==4 || audit.getStatus()==7 || audit.getStatus()==9 || audit.getStatus()==10 || audit.getStatus()==11){
                holder.tvTime.setText(audit.getJxatime());
            }
        }else{
            if(audit.getStatus()==5 || audit.getStatus()==6 || audit.getStatus()==8){
                holder.tvTime.setText(audit.getJhatime());
            }
        }


        /**
         * 显示审核结果名称
         */
        if(i==1 && audit.getAtype()==0){
            holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_FA4D4F));
            holder.imgAudit.setImageResource(R.mipmap.audit_yes);
            switch (audit.getStatus()){
                case 0:
                     holder.tvAudit.setText("教育局审核");
                     holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_333333));
                    holder.imgAudit.setImageResource(R.mipmap.audit_no);
                     break;
                case 3:
                     holder.tvAudit.setText("教育局审核通过");
                     break;
                case 4:
                    holder.tvAudit.setText("教育局审核不通过");
                    break;
                case 7:
                    holder.tvAudit.setText("教育局驳回");
                    break;
                case 10:
                    holder.tvAudit.setText("教育局提交");
                    break;
                default:
                    holder.tvAudit.setText("教育局审核通过");
                    break;
            }
        }else if(i==1 && audit.getAtype()==1){
            holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_FA4D4F));
            holder.imgAudit.setImageResource(R.mipmap.audit_yes);
            switch (audit.getStatus()){
                case 0:
                    holder.tvAudit.setText("学校审核");
                    holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_333333));
                    holder.imgAudit.setImageResource(R.mipmap.audit_no);
                    break;
                case 1:
                    holder.tvAudit.setText("学校审核通过");
                    break;
                case 2:
                    holder.tvAudit.setText("学校审核不通过");
                    break;
                case 9:
                    holder.tvAudit.setText("学校驳回");
                    break;
                case 11:
                    holder.tvAudit.setText("学校提交");
                    break;
                default:
                    holder.tvAudit.setText("学校审核通过");
                    break;
            }
        }else if(i==(getItemCount()-1)){
            holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_FA4D4F));
            holder.imgAudit.setImageResource(R.mipmap.audit_yes);
            switch (audit.getStatus()){
                case 0:
                    holder.tvAudit.setText("基金会审核");
                    holder.tvAudit.setTextColor(context.getResources().getColor(R.color.color_333333));
                    break;
                case 5:
                    holder.tvAudit.setText("基金会审核通过");
                    break;
                case 6:
                    holder.tvAudit.setText("基金会审核不通过");
                    break;
                case 8:
                    holder.tvAudit.setText("基金会驳回");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if(audit.getAtype()==0 || audit.getAtype()==1){
            return 3;
        }
        return 2;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View viewLeft,viewRitht;
        ImageView imgAudit;
        TextView tvAudit,tvTime;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            viewLeft=itemView.findViewById(R.id.view_left);
            viewRitht=itemView.findViewById(R.id.view_right);
            imgAudit=itemView.findViewById(R.id.img_audit);
            tvAudit=itemView.findViewById(R.id.tv_audit);
            tvTime=itemView.findViewById(R.id.tv_time);
        }
    }

}

