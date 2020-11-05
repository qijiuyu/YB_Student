package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.resume.AddEducationActivity;
import com.ylean.yb.student.activity.user.resume.AddSchoolHonorActivity;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.util.JsonUtil;

public class AddResumeEducationAdapter extends RecyclerView.Adapter<AddResumeEducationAdapter.MyHolder> {

    private Activity activity;
    //简历对象
    private ResumeBean.Resume resume;
    public AddResumeEducationAdapter(Activity activity,ResumeBean.Resume resume) {
        super();
        this.activity = activity;
        this.resume=resume;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_education, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final ResumeBean.Education education=resume.getLearningExperienceList().get(i);
        if(education.getType()==3){
            holder.linClass.setVisibility(View.GONE);
            holder.linFaculty.setVisibility(View.VISIBLE);
            holder.linSpecialty.setVisibility(View.VISIBLE);
        }else{
            holder.linClass.setVisibility(View.VISIBLE);
            holder.linFaculty.setVisibility(View.GONE);
            holder.linSpecialty.setVisibility(View.GONE);
        }

        switch (education.getType()){
            case 0:
                holder.tvType.setText("高中");
                break;
            case 1:
                holder.tvType.setText("中职");
                break;
            case 2:
                holder.tvType.setText("高职");
                break;
            case 3:
                holder.tvType.setText("大学");
                break;
            default:
                break;
        }

        switch (education.getEducation()){
            case 0:
                holder.tvEducation.setText("高中");
                break;
            case 1:
                holder.tvEducation.setText("中职");
                break;
            case 2:
                holder.tvEducation.setText("高职");
                break;
            case 3:
                holder.tvEducation.setText("大学专科");
                break;
            case 4:
                holder.tvEducation.setText("大学本科");
                break;
            case 5:
                holder.tvEducation.setText("硕士");
                break;
            case 6:
                holder.tvEducation.setText("博士");
                break;
            default:
                break;
        }
        if(!TextUtils.isEmpty(education.getRegion())){
            final Address address = (Address) JsonUtil.stringToObject(education.getRegion(), Address.class);
            holder.tvProvince.setText(address.getPname());
            holder.tvCity.setText(address.getCname());
            holder.tvArea.setText(address.getAname());
        }
        holder.tvSchool.setText(education.getSchoolName());
        holder.tvFacultyName.setText(education.getFaculty());
        holder.tvSpecialtyName.setText(education.getMajor());
        holder.tvClass.setText(education.getGrades());
        holder.tvTime.setText(education.getAdmissiontime());


        /**
         * 编辑
         */
        holder.tvUpdate.setTag(i);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position= (int) v.getTag();
                Intent intent=new Intent(activity, AddEducationActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("resume",resume);
                activity.startActivityForResult(intent,1000);
            }
        });

    }


    @Override
    public int getItemCount() {
        return resume.getLearningExperienceList()==null ? 0 : resume.getLearningExperienceList().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvType,tvProvince,tvCity,tvArea,tvSchool,tvFacultyName,tvSpecialtyName,tvClass,tvEducation,tvTime,tvUpdate;
        LinearLayout linFaculty,linSpecialty,linClass;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvType=itemView.findViewById(R.id.tv_type);
            tvProvince=itemView.findViewById(R.id.tv_province);
            tvCity=itemView.findViewById(R.id.tv_city);
            tvArea=itemView.findViewById(R.id.tv_area);
            tvSchool=itemView.findViewById(R.id.tv_school);
            tvFacultyName=itemView.findViewById(R.id.tv_faculty);
            tvSpecialtyName=itemView.findViewById(R.id.tv_specialty);
            tvClass=itemView.findViewById(R.id.tv_class);
            tvEducation=itemView.findViewById(R.id.tv_education);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvUpdate=itemView.findViewById(R.id.tv_update);
            linFaculty=itemView.findViewById(R.id.lin_faculty);
            linSpecialty=itemView.findViewById(R.id.lin_specialty);
            linClass=itemView.findViewById(R.id.lin_class);
        }
    }
}

