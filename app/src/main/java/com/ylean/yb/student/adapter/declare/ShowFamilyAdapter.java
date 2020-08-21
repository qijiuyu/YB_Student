package com.ylean.yb.student.adapter.declare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.FamilyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowFamilyAdapter extends BaseAdapter {

    private Activity activity;
    private List<FamilyBean.ListBean> list;
    public ShowFamilyAdapter(Activity activity,List<FamilyBean.ListBean> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_show_family, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final FamilyBean.ListBean listBean=list.get(position);
        switch (listBean.getRelation()){
            case 1:
                holder.tvRelation.setText("父亲");
                break;
            case 2:
                holder.tvRelation.setText("母亲");
                break;
            case 3:
                holder.tvRelation.setText("哥哥");
                break;
            case 4:
                holder.tvRelation.setText("姐姐");
                break;
            case 5:
                holder.tvRelation.setText("弟弟");
                break;
            case 6:
                holder.tvRelation.setText("妹妹");
                break;
            case 7:
                holder.tvRelation.setText(listBean.getRelationname());
                break;
            default:
                break;
        }
        holder.tvName.setText(listBean.getName());
        holder.tvUnit.setText(listBean.getCompany());
        holder.tvPosition.setText(listBean.getOccupation());
        holder.tvEntry.setText(listBean.getIncomesource());
        if(listBean.getWhethersupport()==0){
            holder.tvReward.setText("否");
        }else{
            holder.tvReward.setText("是");
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
        @BindView(R.id.tv_reward)
        TextView tvReward;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
