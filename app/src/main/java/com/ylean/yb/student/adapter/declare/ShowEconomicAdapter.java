package com.ylean.yb.student.adapter.declare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ylean.yb.student.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 经济情况列表
 */
public class ShowEconomicAdapter extends BaseAdapter {

    private Activity activity;
    private String[] data;

    public ShowEconomicAdapter(Activity activity, String[] data) {
        super();
        this.activity = activity;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data==null ? 0 : data.length;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_economic, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(data[position]);
        holder.imgCheck.setImageResource(R.mipmap.check_yes);
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
