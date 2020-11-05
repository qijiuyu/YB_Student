package com.ylean.yb.student.adapter.user;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.school.AddInSchoolActivity;
import com.zxdc.utils.library.bean.InSchoolBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InSchoolAdapter extends BaseAdapter {

    private Activity activity;
    private List<InSchoolBean.InSchool> list;

    public InSchoolAdapter(Activity activity,List<InSchoolBean.InSchool> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_in_school, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final InSchoolBean.InSchool inSchool=list.get(position);
        holder.tvName.setText(inSchool.getName());
        holder.tvContent.setText(inSchool.getContent());
        if(!TextUtils.isEmpty(inSchool.getCreatetime())){
            holder.tvTime.setVisibility(View.VISIBLE);
            holder.tvTime.setText("申请时间："+inSchool.getCreatetime());
        }else{
            holder.tvTime.setVisibility(View.GONE);
        }
        switch (inSchool.getType()){
            case 1:
                 holder.tvType.setText("学校提交");
                 break;
            case 2:
                holder.tvType.setText("学生提交");
                break;
            case 3:
                holder.tvType.setText("基金会提交");
                break;
            default:
                break;
        }


        /**
         * 学生提交的显示提交按钮
         */
        if(inSchool.getType()==2){
            holder.tvSubmit.setVisibility(View.VISIBLE);
        }else{
            holder.tvSubmit.setVisibility(View.GONE);
        }


        /**
         * 审核状态
         */
        switch (inSchool.getCheckstatus()){
            case 0:
                holder.tvStatus.setText("未提交");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_007AFF));
                holder.tvSubmit.setText("立即提交");
                break;
            case 2:
                holder.tvStatus.setText("待提交");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FFA000));
                holder.tvSubmit.setVisibility(View.GONE);
                break;
            case 3:
                holder.tvStatus.setText("待审核");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FFA000));
                holder.tvSubmit.setVisibility(View.GONE);
                break;
            case 4:
                holder.tvStatus.setText("驳回");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_FA4D4F));
                holder.tvSubmit.setText("重新提交");
                break;
            case 5:
                holder.tvStatus.setText("通过");
                holder.tvStatus.setTextColor(activity.getResources().getColor(R.color.color_049640));
                holder.tvSubmit.setVisibility(View.GONE);
                break;
            default:
                break;
        }


        /**
         * 提交在校情况
         */
        holder.tvSubmit.setTag(inSchool);
        holder.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InSchoolBean.InSchool inSchool= (InSchoolBean.InSchool) v.getTag();
                Intent intent=new Intent(activity, AddInSchoolActivity.class);
                intent.putExtra("inSchool",inSchool);
                activity.startActivityForResult(intent,1000);
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_submit)
        TextView tvSubmit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
