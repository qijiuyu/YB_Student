package com.ylean.yb.student.adapter.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.main.NewsDetailsActivity;
import com.zxdc.utils.library.bean.NewsListBean;

import java.util.List;

public class MainJLAdapter extends RecyclerView.Adapter<MainJLAdapter.MyHolder> {

    private Context context;
    private List<NewsListBean.ListBean> list;
    public MainJLAdapter(Context context,List<NewsListBean.ListBean> list) {
        super();
        this.context = context;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_main_jl, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        final NewsListBean.ListBean listBean=list.get(i);
        //显示图片
        String imgUrl = listBean.getImgurl();
        if(!TextUtils.isEmpty(imgUrl)){
            holder.imgHead.setTag(R.id.imageid, imgUrl);
            if (holder.imgHead.getTag(R.id.imageid) != null && imgUrl == holder.imgHead.getTag(R.id.imageid)) {
                Glide.with(context).load(imgUrl).into(holder.imgHead);
            }
        }
        holder.tvContent.setText(listBean.getSubtitle());
        if(!TextUtils.isEmpty(listBean.getCreatetime())){
            holder.tvTime.setText(listBean.getCreatetime().split(" ")[0]);
        }
        holder.tvLookNum.setText(String.valueOf(listBean.getYcount()));

        /**
         * 进入详情页面
         */
        holder.linClick.setTag(listBean.getId());
        holder.linClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.putExtra("id",(int)v.getTag());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout linClick;
        ImageView imgHead;
        TextView tvContent,tvTime,tvLookNum;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            linClick=itemView.findViewById(R.id.lin_click);
            imgHead=itemView.findViewById(R.id.image);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvLookNum=itemView.findViewById(R.id.tv_look_num);
        }
    }

}

