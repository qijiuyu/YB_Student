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
import com.ylean.yb.student.activity.user.resume.AddSchoolHonorActivity;
import com.zxdc.utils.library.bean.ResumeBean;

public class AddResumeHonorAdapter extends RecyclerView.Adapter<AddResumeHonorAdapter.MyHolder> {

    private Activity activity;
    //简历对象
    private ResumeBean.Resume resume;
    public AddResumeHonorAdapter(Activity activity, ResumeBean.Resume resume) {
        super();
        this.activity = activity;
        this.resume=resume;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_honor, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final ResumeBean.Honor honor=resume.getInSchoolHonorList().get(i);
       holder.tvTime.setText(honor.getAcquisitionTime());
       holder.tvAward.setText(honor.getPrize());
       holder.tvLevel.setText(honor.getLevel());

        /**
         * 编辑
         */
        holder.tvUpdate.setTag(i);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position= (int) v.getTag();
                Intent intent=new Intent(activity, AddSchoolHonorActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("resume",resume);
                activity.startActivityForResult(intent,1001);
            }
        });
    }


    @Override
    public int getItemCount() {
        return resume.getInSchoolHonorList()==null ? 0 : resume.getInSchoolHonorList().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTime,tvAward,tvLevel,tvUpdate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvAward=itemView.findViewById(R.id.tv_award);
            tvLevel=itemView.findViewById(R.id.tv_level);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

