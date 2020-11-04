package com.ylean.yb.student.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.UploadFileActivity;
import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JZAdapter extends BaseAdapter {

    private Activity activity;
    private List<DonationBean.ListBean> list;

    public JZAdapter(Activity activity,List<DonationBean.ListBean> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_list_jz, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final DonationBean.ListBean listBean=list.get(position);
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
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.image)
        ImageView imgHead;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_down)
        TextView tvDown;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
