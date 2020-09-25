package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.resume.AddSpecialtyActivity;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.util.JsonUtil;

import java.util.List;

public class EditResumeSpecialtyAdapter extends RecyclerView.Adapter<EditResumeSpecialtyAdapter.MyHolder> {

    private Activity activity;
    private List<ResumeBean.Speciality> list;
    private int resumeId;//简历id
    public EditResumeSpecialtyAdapter(Activity activity, String msg,int resumeId) {
        super();
        this.activity = activity;
        this.resumeId=resumeId;
        if(!TextUtils.isEmpty(msg)){
            list= JsonUtil.stringToList(msg,ResumeBean.Speciality.class);
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_specialty, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final ResumeBean.Speciality speciality=list.get(i);
       holder.tvLanguage.setText(speciality.getName());
       holder.tvMaster.setText(speciality.getLevel());


        /**
         * 编辑
         */
        holder.tvUpdate.setTag(speciality);
       holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final ResumeBean.Speciality speciality= (ResumeBean.Speciality) v.getTag();
               Intent intent=new Intent(activity, AddSpecialtyActivity.class);
               intent.putExtra("resumeId",resumeId);
               intent.putExtra("speciality",speciality);
               activity.startActivityForResult(intent,1003);
           }
       });
    }




    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvMaster,tvLanguage,tvUpdate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvLanguage=itemView.findViewById(R.id.tv_language);
            tvMaster=itemView.findViewById(R.id.tv_master);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

