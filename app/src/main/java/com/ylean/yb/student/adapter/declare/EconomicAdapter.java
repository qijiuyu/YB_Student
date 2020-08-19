package com.ylean.yb.student.adapter.declare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.EconomicBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 经济情况列表
 */
public class EconomicAdapter extends BaseAdapter {

    private Activity activity;
    private List<EconomicBean.Economic> list;

    public EconomicAdapter(Activity activity,List<EconomicBean.Economic> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_economic, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final EconomicBean.Economic economic=list.get(position);
        holder.tvName.setText(economic.getName());
        if(economic.isSelect()){
            holder.imgCheck.setImageResource(R.mipmap.check_yes);
        }else{
            holder.imgCheck.setImageResource(R.mipmap.check_no);
        }

        holder.linClick.setTag(economic);
        holder.linClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EconomicBean.Economic economic= (EconomicBean.Economic) v.getTag();
                if(economic.isSelect()){
                    economic.setSelect(false);
                }else{
                    economic.setSelect(true);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.lin_click)
        LinearLayout linClick;
        @BindView(R.id.img_check)
        ImageView imgCheck;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
