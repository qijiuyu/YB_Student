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
import com.zxdc.utils.library.bean.BatchBean;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DeclareHeadViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<BatchBean.Batch> list;

    public DeclareHeadViewAdapter(Activity activity, List<BatchBean.Batch> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_declare_head, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final BatchBean.Batch batch=list.get(position);
        //显示图片
        String imgUrl = batch.getImg();
        if(!TextUtils.isEmpty(imgUrl)){
            holder.imgHead.setTag(R.id.imageid, imgUrl);
            if (holder.imgHead.getTag(R.id.imageid) != null && imgUrl == holder.imgHead.getTag(R.id.imageid)) {
                Glide.with(activity).load(imgUrl).into(holder.imgHead);
            }
        }
        holder.tvName.setText(batch.getName());
        holder.tvContent.setText(batch.getRemarks());
        holder.tvNum.setText("已有："+batch.getApplynum()+"人 进行申请");
        holder.tvValidTime.setText("有效时间："+batch.getStarttime().split(" ")[0]+"-"+batch.getEndtime().split(" ")[0]);
        holder.tvSendTime.setText("发布时间："+batch.getCreatetime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_valid_time)
        TextView tvValidTime;
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.tv_submit)
        TextView tvSubmit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
