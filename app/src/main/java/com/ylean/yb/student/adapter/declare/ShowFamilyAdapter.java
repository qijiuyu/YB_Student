package com.ylean.yb.student.adapter.declare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowFamilyAdapter extends BaseAdapter {

    private Activity activity;

    public ShowFamilyAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 2;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_show_family, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_relation)
        TextView tvRelation;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.tv_entry)
        TextView tvEntry;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
