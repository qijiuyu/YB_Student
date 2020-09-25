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
import com.ylean.yb.student.activity.user.resume.AddSchoolPositionActivity;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.parameter.Time;
import com.zxdc.utils.library.util.JsonUtil;

public class AddResumePositionAdapter extends RecyclerView.Adapter<AddResumePositionAdapter.MyHolder> {

    private Activity activity;
    //简历对象
    private ResumeBean.Resume resume;
    public AddResumePositionAdapter(Activity activity, ResumeBean.Resume resume) {
        super();
        this.activity = activity;
        this.resume=resume;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_position, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final ResumeBean.Position position=resume.getSchoolDutiesList().get(i);
       if(!TextUtils.isEmpty(position.getTimes())){
           final Time time= (Time) JsonUtil.stringToObject(position.getTimes(),Time.class);
           holder.tvStartTime.setText(time.getStart());
           holder.tvEndTime.setText(time.getEnd());
       }
       holder.tvName.setText(position.getName());
       holder.tvMemo.setText(position.getDescription());

        /**
         * 编辑
         */
        holder.tvUpdate.setTag(i);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position= (int) v.getTag();
                Intent intent=new Intent(activity, AddSchoolPositionActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("resume",resume);
                activity.startActivityForResult(intent,1002);
            }
        });
    }



    @Override
    public int getItemCount() {
        return resume.getSchoolDutiesList()==null ? 0 : resume.getSchoolDutiesList().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime,tvEndTime,tvName,tvMemo,tvUpdate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvStartTime=itemView.findViewById(R.id.tv_startTime);
            tvEndTime=itemView.findViewById(R.id.tv_endTime);
            tvName=itemView.findViewById(R.id.tv_name);
            tvMemo=itemView.findViewById(R.id.tv_memo);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

