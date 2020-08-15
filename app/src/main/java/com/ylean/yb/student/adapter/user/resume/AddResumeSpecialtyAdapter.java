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
import com.zxdc.utils.library.bean.AddResumeSpecialty;
import java.util.List;

public class AddResumeSpecialtyAdapter extends RecyclerView.Adapter<AddResumeSpecialtyAdapter.MyHolder> {

    private Activity activity;
    private List<AddResumeSpecialty> list;

    /**
     * 当前输入的对象
     */
    private AddResumeSpecialty specialty;
    public AddResumeSpecialtyAdapter(Activity activity, List<AddResumeSpecialty> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddResumeSpecialty());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_resume_add_specialty, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddResumeSpecialty addResumeSpecialty=list.get(i);

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
        TextView tvMaster;
        EditText etLanguage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            etLanguage=itemView.findViewById(R.id.et_language);
            tvMaster=itemView.findViewById(R.id.tv_master);
        }
    }
}

