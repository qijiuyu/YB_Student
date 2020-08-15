package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.zxdc.utils.library.bean.AddEducation;
import com.zxdc.utils.library.bean.AddResumePostion;

import java.util.List;

public class AddResumePositionAdapter extends RecyclerView.Adapter<AddResumePositionAdapter.MyHolder> {

    private Activity activity;
    private List<AddResumePostion> list;

    /**
     * 当前输入的对象
     */
    private AddResumePostion position;
    public AddResumePositionAdapter(Activity activity, List<AddResumePostion> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddResumePostion());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_position, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddResumePostion addResumePostion=list.get(i);


        /**
         * 选择开始时间
         */
        holder.tvStartTime.setTag(addResumePostion);
        holder.tvStartTime.setOnClickListener(new View.OnClickListener() {
           public void onClick(final View v) {
               position= (AddResumePostion) v.getTag();
               SelectTimeUtils.selectTime(activity, new TimeCallBack() {
                   public void getTime(String time) {
                       ((TextView)v).setText(time);
                   }
               });
           }
       });

        /**
         * 选择结束时间
         */
        holder.tvEndTime.setTag(addResumePostion);
        holder.tvEndTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                position= (AddResumePostion) v.getTag();
                SelectTimeUtils.selectTime(activity, new TimeCallBack() {
                    public void getTime(String time) {
                        ((TextView)v).setText(time);
                    }
                });
            }
        });


    }



    /**
     * 监听输入框
     */
    TextWatcher textWatcher=new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {
            String content=s.toString().trim();
            if(TextUtils.isEmpty(content)){
                return;
            }

        }
    };



    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime,tvEndTime;
        EditText etName,etMemo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvStartTime=itemView.findViewById(R.id.tv_startTime);
            tvEndTime=itemView.findViewById(R.id.tv_endTime);
            etName=itemView.findViewById(R.id.et_name);
            etMemo=itemView.findViewById(R.id.et_memo);
        }
    }
}

