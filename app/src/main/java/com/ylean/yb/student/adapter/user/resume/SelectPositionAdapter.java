package com.ylean.yb.student.adapter.user.resume;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.ResumePostion;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPositionAdapter extends BaseAdapter {

    private Activity activity;
    private List<ResumePostion.Position> list;

    public SelectPositionAdapter(Activity activity, List<ResumePostion.Position> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_select_position, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ResumePostion.Position po=list.get(position);
        holder.tvName.setText(po.getPositionName());
        if(po.getSelectId()!=0){
            holder.imgSelect.setImageResource(R.mipmap.check_yes);
        }else{
            holder.imgSelect.setImageResource(R.mipmap.check_no);
        }

        holder.linClick.setTag(po);
        holder.linClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResumePostion.Position po= (ResumePostion.Position) v.getTag();
                if(po.getSelectId()==0){
                    po.setSelectId(po.getPositionId());
                }else{
                    po.setSelectId(0);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_select)
        ImageView imgSelect;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.lin_click)
        LinearLayout linClick;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
