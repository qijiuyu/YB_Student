package com.ylean.yb.student.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.UploadFileActivity;
import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.util.JsonUtil;
import java.util.List;

/**
 * 捐赠
 */
public class MainJZAdapter extends RecyclerView.Adapter<MainJZAdapter.MyHolder> {

    private Activity activity;
    private List<DonationBean.ListBean> list;

    public MainJZAdapter(Activity activity,List<DonationBean.ListBean> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_main_jz, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        final DonationBean.ListBean listBean=list.get(i);
        //显示图片
        String imgUrl = listBean.getImgurl();
        if(!TextUtils.isEmpty(imgUrl)){
            holder.imgHead.setTag(R.id.imageid, imgUrl);
            if (holder.imgHead.getTag(R.id.imageid) != null && imgUrl == holder.imgHead.getTag(R.id.imageid)) {
                Glide.with(activity).load(imgUrl).into(holder.imgHead);
            }
        }
        holder.tvContent.setText(listBean.getContent());
        holder.tvTime.setText("申报截止日期："+listBean.getEndtime());

        /**
         * 下载文件
         */
        holder.tvDown.setTag(listBean.getFiles());
        holder.tvDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String file= (String) v.getTag();
                if(TextUtils.isEmpty(file)){
                    return;
                }
                final String[] files= (String[]) JsonUtil.stringToObject(file,String[].class);
                Intent intent=new Intent(activity, UploadFileActivity.class);
                intent.putExtra("fileUrl",files[0]);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgHead;
        TextView tvContent,tvTime,tvDown;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.image);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvDown=itemView.findViewById(R.id.tv_down);
        }
    }

}

