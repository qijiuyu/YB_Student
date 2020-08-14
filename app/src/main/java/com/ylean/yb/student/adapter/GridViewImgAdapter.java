package com.ylean.yb.student.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.entity.LocalMedia;
import com.ylean.yb.student.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GridViewImgAdapter extends BaseAdapter {

    private Activity context;
    public List<LocalMedia> list;
    public GridViewImgAdapter(Activity context, List<LocalMedia> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size()+1;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(position==list.size()){
            holder.image.setImageResource(R.mipmap.add_img);
        }else{
            Glide.with(context).load(list.get(position).getCompressPath()).into(holder.image);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
