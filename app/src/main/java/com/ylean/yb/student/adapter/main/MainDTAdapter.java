package com.ylean.yb.student.adapter.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylean.yb.student.R;

public class MainDTAdapter extends RecyclerView.Adapter<MainDTAdapter.MyHolder> {

    private Context context;
    public MainDTAdapter(Context context) {
        super();
        this.context = context;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_main_dt, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvContent,tvTime,tvLookNum;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img_head);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvLookNum=itemView.findViewById(R.id.tv_look_num);
        }
    }

}

