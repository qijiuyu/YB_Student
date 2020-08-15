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
import com.zxdc.utils.library.bean.AddResumeCertificate;
import java.util.List;

public class AddResumeCertificateAdapter extends RecyclerView.Adapter<AddResumeCertificateAdapter.MyHolder> {

    private Activity activity;
    private List<AddResumeCertificate> list;

    /**
     * 当前输入的对象
     */
    private AddResumeCertificate certificate;
    public AddResumeCertificateAdapter(Activity activity, List<AddResumeCertificate> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddResumeCertificate());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_certificate, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddResumeCertificate addResumeCertificate=list.get(i);


        /**
         * 选择时间
         */
        holder.tvTime.setTag(addResumeCertificate);
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
           public void onClick(final View v) {
               certificate= (AddResumeCertificate) v.getTag();
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
        EditText etName,etMemo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            etName=itemView.findViewById(R.id.et_name);
            etMemo=itemView.findViewById(R.id.et_memo);
            tvTime=itemView.findViewById(R.id.tv_time);
        }
    }
}

