package com.ylean.yb.student.adapter.main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.NewsTitleBean;
import com.zxdc.utils.library.util.LogUtils;

import java.util.List;

public class CultrueAdapter extends RecyclerView.Adapter<CultrueAdapter.MyHolder> {

    private Activity activity;
    private List<NewsTitleBean.ListBean> list;
    //标题位置
    private int position;
    private ClickCallBack clickCallBack;
    public CultrueAdapter(Activity activity, List<NewsTitleBean.ListBean> list,ClickCallBack clickCallBack) {
        super();
        this.activity = activity;
        this.list=list;
        this.clickCallBack=clickCallBack;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_cultrue_title, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
      final NewsTitleBean.ListBean listBean=list.get(i);
      holder.tvTitle.setText(listBean.getName());
      if(position==i){
          holder.tvTitle.setTextColor(activity.getResources().getColor(android.R.color.white));
          holder.tvTitle.setBackground(activity.getResources().getDrawable(R.drawable.bg_cultrue_title));
      }else{
          holder.tvTitle.setTextColor(activity.getResources().getColor(android.R.color.black));
          holder.tvTitle.setBackground(activity.getResources().getDrawable(R.drawable.bg_cultrue_title_no));
      }

     /**
      * 点击标题
      */
      holder.tvTitle.setTag(i);
      holder.tvTitle.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              position= (int) v.getTag();
              notifyDataSetChanged();
              clickCallBack.onClick(position);
          }
      });
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }


    public void setPosition(int position){
        this.position=position;
        notifyDataSetChanged();
    }


    public interface ClickCallBack{
        void onClick(int position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
        }
    }
}

