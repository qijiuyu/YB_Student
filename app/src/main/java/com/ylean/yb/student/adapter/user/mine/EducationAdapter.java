package com.ylean.yb.student.adapter.user.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.min.AddEducationActivity;
import com.ylean.yb.student.persenter.EducationP;
import com.zxdc.utils.library.bean.EducationBean;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyHolder> {

    private Activity activity;
    private List<EducationBean.Education> list;
    private EducationP educationP;
    public EducationAdapter(Activity activity, List<EducationBean.Education> list,EducationP educationP) {
        super();
        this.activity = activity;
        this.list=list;
        this.educationP=educationP;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_education, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final EducationBean.Education education=list.get(i);
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
       holder.tvClass.setText(education.getGrades());
       holder.tvTime.setText(education.getAdmissiontime());

        /**
         * 删除
         */
        holder.tvDelete.setTag(education);
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                educationP.deleteEducation((EducationBean.Education) v.getTag());
            }
        });


        /**
         * 编辑
         */
        holder.tvUpdate.setTag(education);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EducationBean.Education education= (EducationBean.Education) v.getTag();
                Intent intent=new Intent(activity, AddEducationActivity.class);
                intent.putExtra("education",education);
                activity.startActivityForResult(intent,1000);
            }
        });
    }





    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvType,tvProvince,tvCity,tvArea,tvSchool,tvFaculty,tvSpecialty,tvClass,tvEducation,tvTime,tvDelete,tvUpdate;
        LinearLayout linFaculty,linSpecialty,linClass;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvType=itemView.findViewById(R.id.tv_type);
            tvProvince=itemView.findViewById(R.id.tv_province);
            tvCity=itemView.findViewById(R.id.tv_city);
            tvArea=itemView.findViewById(R.id.tv_area);
            tvSchool=itemView.findViewById(R.id.tv_school);
            tvFaculty=itemView.findViewById(R.id.tv_faculty);
            tvSpecialty=itemView.findViewById(R.id.tv_specialty);
            tvClass=itemView.findViewById(R.id.tv_class);
            tvEducation=itemView.findViewById(R.id.tv_education);
            tvTime=itemView.findViewById(R.id.tv_time);
            linFaculty=itemView.findViewById(R.id.lin_faculty);
            linSpecialty=itemView.findViewById(R.id.lin_specialty);
            linClass=itemView.findViewById(R.id.lin_class);
            tvDelete=itemView.findViewById(R.id.tv_delete);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

