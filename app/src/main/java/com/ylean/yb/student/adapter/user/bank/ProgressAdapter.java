package com.ylean.yb.student.adapter.user.bank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.BankProgress;
import com.zxdc.utils.library.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressAdapter extends BaseAdapter {

    private Activity activity;
    private List<BankProgress.Progress> list;

    public ProgressAdapter(Activity activity,List<BankProgress.Progress> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_bank_progress, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final BankProgress.Progress progress=list.get(position);
        holder.tvContent.setText(progress.getJvalue());
        holder.tvTime.setText(progress.getCreatetime());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_status)
        CircleImageView imgStatus;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
