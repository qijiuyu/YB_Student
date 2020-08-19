package com.ylean.yb.student.adapter.user.mine;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.callback.TimeCallBack;
import com.ylean.yb.student.enumer.AddEducationEnum;
import com.ylean.yb.student.utils.SelectTimeUtils;
import com.ylean.yb.student.view.SchoolType;
import com.zxdc.utils.library.bean.AddEducation;
import java.util.List;

public class AddEducationAdapter extends RecyclerView.Adapter<AddEducationAdapter.MyHolder> {

    private Activity activity;
    private List<AddEducation> list;
    public AddEducationAdapter(Activity activity, List<AddEducation> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddEducation());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_education, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddEducation addEducation=list.get(i);




    }





    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvType,tvProvince,tvCity,tvArea,tvSchool,tvTime;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvType=itemView.findViewById(R.id.tv_type);
            tvProvince=itemView.findViewById(R.id.tv_province);
            tvCity=itemView.findViewById(R.id.tv_city);
            tvArea=itemView.findViewById(R.id.tv_area);
            tvSchool=itemView.findViewById(R.id.tv_school);
            tvTime=itemView.findViewById(R.id.tv_time);
        }
    }
}

