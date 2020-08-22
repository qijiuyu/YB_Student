package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.Address;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.util.JsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyEducationAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumeBean.Education> list;
    public MyEducationAdapter(Activity activity,List<ResumeBean.Education> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_resume_education, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final ResumeBean.Education education=list.get(position);
        if(education.getType()==3){
            holder.tvClass.setVisibility(View.GONE);
            holder.tvFacultyName.setVisibility(View.VISIBLE);
            holder.tvSpecialtyName.setVisibility(View.VISIBLE);
        }else{
            holder.tvClass.setVisibility(View.VISIBLE);
            holder.tvFacultyName.setVisibility(View.GONE);
            holder.tvSpecialtyName.setVisibility(View.GONE);
        }
        switch (education.getType()){
            case 0:
                holder.tvSchoolType.setText("学校类型：高中");
                break;
            case 1:
                holder.tvSchoolType.setText("学校类型：中职");
                break;
            case 2:
                holder.tvSchoolType.setText("学校类型：高职");
                break;
            case 3:
                holder.tvSchoolType.setText("学校类型：大学");
                break;
            default:
                break;
        }

        switch (education.getEducation()){
            case 0:
                holder.tvEducation.setText("学历：高中");
                break;
            case 1:
                holder.tvEducation.setText("学历：中职");
                break;
            case 2:
                holder.tvEducation.setText("学历：高职");
                break;
            case 3:
                holder.tvEducation.setText("学历：大学专科");
                break;
            case 4:
                holder.tvEducation.setText("学历：大学本科");
                break;
            case 5:
                holder.tvEducation.setText("学历：硕士");
                break;
            case 6:
                holder.tvEducation.setText("学历：博士");
                break;
            default:
                break;
        }
        if(!TextUtils.isEmpty(education.getRegion())){
            final Address address = (Address) JsonUtil.stringToObject(education.getRegion(), Address.class);
            holder.tvSchoolAddress.setText("所在地区："+address.getPname()+","+address.getCname()+","+address.getAname());
        }
        holder.tvSchoolName.setText("所在学校："+education.getSchoolName());
        holder.tvClass.setText("班级："+education.getGrades());
        holder.tvTime.setText("入学时间："+education.getAdmissiontime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_school_type)
        TextView tvSchoolType;
        @BindView(R.id.tv_school_address)
        TextView tvSchoolAddress;
        @BindView(R.id.tv_school_name)
        TextView tvSchoolName;
        @BindView(R.id.tv_faculty_name)
        TextView tvFacultyName;
        @BindView(R.id.tv_specialty_name)
        TextView tvSpecialtyName;
        @BindView(R.id.tv_class)
        TextView tvClass;
        @BindView(R.id.tv_education)
        TextView tvEducation;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
