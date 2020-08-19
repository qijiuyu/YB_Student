package com.ylean.yb.student.adapter.user.mine;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectCallBack;
import com.ylean.yb.student.persenter.FamilyP;
import com.ylean.yb.student.view.AddFamilyView;
import com.zxdc.utils.library.bean.FamilyBean;
import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.MyHolder> {

    private Activity activity;
    private List<FamilyBean.ListBean> list;
    private FamilyP familyP;
    public FamilyAdapter(Activity activity, List<FamilyBean.ListBean> list,FamilyP familyP) {
        super();
        this.activity = activity;
        this.list=list;
        this.familyP=familyP;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_family, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final FamilyBean.ListBean listBean=list.get(i);
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
       holder.etName.setText(listBean.getName());
       holder.etUnit.setText(listBean.getCompany());
       holder.etPosition.setText(listBean.getOccupation());
       holder.etEntry.setText(listBean.getIncomesource());
       if(listBean.getWhethersupport()==0){
           holder.tvReward.setText("否");
       }else{
           holder.tvReward.setText("是");
       }


        /**
         * 删除
         */
        holder.tvDelete.setTag(listBean);
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               familyP.deleteFamily((FamilyBean.ListBean) v.getTag());
           }
       });


        /**
         * 编辑
         */
        holder.tvUpdate.setTag(listBean);
        holder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddFamilyView(activity, (FamilyBean.ListBean) v.getTag(), new SelectCallBack() {
                    @Override
                    public void selectBack(Object object) {
                        //查询家庭成员数据
                        familyP.getFamilyList();
                    }
                }).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvRelation,tvReward;
        TextView etName,etUnit,etPosition,etEntry,tvDelete,tvUpdate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvRelation=itemView.findViewById(R.id.tv_relation);
            tvReward=itemView.findViewById(R.id.tv_reward);
            etName=itemView.findViewById(R.id.et_name);
            etUnit=itemView.findViewById(R.id.et_unit);
            etPosition=itemView.findViewById(R.id.et_position);
            etEntry=itemView.findViewById(R.id.et_entry);
            tvDelete=itemView.findViewById(R.id.tv_delete);
            tvUpdate=itemView.findViewById(R.id.tv_update);
        }
    }
}

