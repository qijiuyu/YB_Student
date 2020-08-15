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
import com.zxdc.utils.library.bean.AddHonor;

import java.util.List;

public class AddResumeHonorAdapter extends RecyclerView.Adapter<AddResumeHonorAdapter.MyHolder> {

    private Activity activity;
    private List<AddHonor> list;

    /**
     * 当前输入的对象
     */
    private AddHonor honor;
    public AddResumeHonorAdapter(Activity activity, List<AddHonor> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddHonor());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_honor, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddHonor addHonor=list.get(i);


        /**
         * 选择时间
         */
        holder.tvTime.setTag(addHonor);
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
           public void onClick(final View v) {
               honor= (AddHonor) v.getTag();
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
        TextView tvTime;
        EditText etAward,etLevel;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            etAward=itemView.findViewById(R.id.et_award);
            etLevel=itemView.findViewById(R.id.et_level);
            tvTime=itemView.findViewById(R.id.tv_time);
        }
    }
}

