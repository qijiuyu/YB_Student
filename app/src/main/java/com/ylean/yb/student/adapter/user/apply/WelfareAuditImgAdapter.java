package com.ylean.yb.student.adapter.user.apply;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ylean.yb.student.R;

public class WelfareAuditImgAdapter extends RecyclerView.Adapter<WelfareAuditImgAdapter.MyHolder> {

    private Activity activity;
    public WelfareAuditImgAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_welfare_audit_img, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {


    }





    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
        }
    }
}

