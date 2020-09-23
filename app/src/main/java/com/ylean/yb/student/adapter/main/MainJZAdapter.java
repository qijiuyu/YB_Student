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

/**
 * 捐赠
 */
public class MainJZAdapter extends RecyclerView.Adapter<MainJZAdapter.MyHolder> {

    private Context context;
    public MainJZAdapter(Context context) {
        super();
        this.context = context;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_main_jz, viewGroup,false);
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
        TextView tvContent,tvTime,tvDown;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvDown=itemView.findViewById(R.id.tv_down);
        }
    }

}

