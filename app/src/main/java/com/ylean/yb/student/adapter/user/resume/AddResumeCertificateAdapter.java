package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.resume.AddCertificateActivity;
import com.zxdc.utils.library.bean.ResumeBean;
import java.util.List;

public class AddResumeCertificateAdapter extends RecyclerView.Adapter<AddResumeCertificateAdapter.MyHolder> {

    private Activity activity;
    private List<ResumeBean.Certificate> list;
    public AddResumeCertificateAdapter(Activity activity, List<ResumeBean.Certificate> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_certificate, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final ResumeBean.Certificate certificate=list.get(i);
       holder.tvTime.setText(certificate.getAcquisitionTime());
       holder.tvName.setText(certificate.getName());
       holder.tvMemo.setText(certificate.getRemarks());


        /**
         * 编辑
         */
        holder.tvUpdate.setTag(certificate);
       holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ResumeBean.Certificate certificate= (ResumeBean.Certificate) v.getTag();
               Intent intent=new Intent(activity, AddCertificateActivity.class);
               intent.putExtra("certificate",certificate);
               activity.startActivityForResult(intent,1004);
           }
       });
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTime,tvName,tvMemo,tvDelete,tvUpdate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvMemo=itemView.findViewById(R.id.tv_memo);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvDelete=itemView.findViewById(R.id.tv_delete);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

