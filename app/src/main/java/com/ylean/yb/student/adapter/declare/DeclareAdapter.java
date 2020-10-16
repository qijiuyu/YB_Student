package com.ylean.yb.student.adapter.declare;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.DeclareBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeclareAdapter extends BaseAdapter {

    private Activity activity;
    private List<DeclareBean.Declare> list;

    public DeclareAdapter(Activity activity,List<DeclareBean.Declare> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_declare, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final DeclareBean.Declare declare=list.get(position);
        holder.tvName.setText(declare.getBname());
        //显示图片
        String imgUrl = declare.getImg();
        if(!TextUtils.isEmpty(imgUrl)){
            holder.imgHead.setTag(R.id.imageid, imgUrl);
            if (holder.imgHead.getTag(R.id.imageid) != null && imgUrl == holder.imgHead.getTag(R.id.imageid)) {
                Glide.with(activity).load(imgUrl).into(holder.imgHead);
            }
        }
        holder.tvContent.setText("考入学校："+declare.getSiname());
        if(!TextUtils.isEmpty(declare.getCreatetime())){
            holder.tvTime.setText("提交时间："+declare.getCreatetime().split(" ")[0]);
        }
        switch (declare.getBdstatus()){
            case 0:
                 holder.tvStatus.setText("未审核");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FFA000));
                 break;
            case 1:
                 holder.tvStatus.setText("学校审核通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_049640));
                 break;
            case 3:
                 holder.tvStatus.setText("教育局审核通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_049640));
                 break;
            case 5:
                 holder.tvStatus.setText("基金会审核通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_049640));
                 break;
            case 2:
                 holder.tvStatus.setText("学校审核不通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                 break;
            case 4:
                 holder.tvStatus.setText("教育局审核不通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                 break;
            case 6:
                 holder.tvStatus.setText("基金会审核不通过");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                 break;
            case 7:
                 holder.tvStatus.setText("教育局驳回");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                 break;
            case 8:
                 holder.tvStatus.setText("基金会驳回");
                 holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                 break;
            case 9:
                holder.tvStatus.setText("学校驳回");
                holder.tvStatus.setBackgroundColor(activity.getResources().getColor(R.color.color_FA4D4F));
                break;
             default:
                 break;
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
