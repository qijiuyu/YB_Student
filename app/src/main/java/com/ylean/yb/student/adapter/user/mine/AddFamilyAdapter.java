package com.ylean.yb.student.adapter.user.mine;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.enumer.AddFarilyEnum;
import com.ylean.yb.student.view.SelectRelation;
import com.ylean.yb.student.view.SelectReward;
import com.zxdc.utils.library.bean.AddFamily;
import java.util.List;

public class AddFamilyAdapter extends RecyclerView.Adapter<AddFamilyAdapter.MyHolder> {

    private Activity activity;
    private List<AddFamily> list;
    /**
     * 当前状态
     */
    private AddFarilyEnum addFarilyEnum;

    /**
     * 当前输入的对象
     */
    private AddFamily family;
    public AddFamilyAdapter(Activity activity, List<AddFamily> list) {
        super();
        this.activity = activity;
        this.list=list;
        if(list.size()==0){
            list.add(new AddFamily());
        }
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_add_family, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
       final AddFamily addFamily=list.get(i);

        /**
         * 选择关系
         */
        holder.tvRelation.setTag(addFamily);
        holder.tvRelation.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               family= (AddFamily) v.getTag();
               new SelectRelation(activity, new SelectRelationCallBack() {
                   public void onSuccess(Object object, Object object2) {
                       holder.tvRelation.setText((String)object);
                       family.setRelation((String)object2);
                   }
               }).show();
           }
       });


        /**
         * 监听姓名的输入
         */
        holder.etName.setTag(addFamily);
        holder.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    addFarilyEnum=AddFarilyEnum.姓名;
                    family= (AddFamily) v.getTag();
                    holder.etName.addTextChangedListener(textWatcher);
                }else{
                    holder.etName.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 监听单位的输入
         */
        holder.etUnit.setTag(addFamily);
        holder.etUnit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    addFarilyEnum=AddFarilyEnum.单位;
                    family= (AddFamily) v.getTag();
                    holder.etUnit.addTextChangedListener(textWatcher);
                }else{
                    holder.etUnit.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 监听职业的输入
         */
        holder.etPosition.setTag(addFamily);
        holder.etPosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    addFarilyEnum=AddFarilyEnum.职位;
                    family= (AddFamily) v.getTag();
                    holder.etPosition.addTextChangedListener(textWatcher);
                }else{
                    holder.etPosition.removeTextChangedListener(textWatcher);
                }
            }
        });


        /**
         * 监听收入来源的输入
         */
        holder.etEntry.setTag(addFamily);
        holder.etEntry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    addFarilyEnum=AddFarilyEnum.收入来源;
                    family= (AddFamily) v.getTag();
                    holder.etEntry.addTextChangedListener(textWatcher);
                }else{
                    holder.etEntry.removeTextChangedListener(textWatcher);
                }
            }
        });



        /**
         * 是否接受过燕宝奖励
         */
        holder.tvReward.setTag(addFamily);
        holder.tvReward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                family= (AddFamily) v.getTag();
                new SelectReward(activity, new SelectRelationCallBack() {
                    public void onSuccess(Object object, Object object2) {
                        holder.tvReward.setText((String)object);
                        family.setWhethersupport((String)object2);
                    }
                }).show();
            }
        });
    }



    /**
     * 监听输入框
     */
    TextWatcher textWatcher=new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {
            String content=s.toString().trim();
            if(TextUtils.isEmpty(content)){
                return;
            }
            switch (addFarilyEnum){
                case 姓名:
                    family.setName(content);
                    break;
                case 单位:
                    family.setCompany(content);
                    break;
                case 职位:
                    family.setOccupation(content);
                    break;
                case 收入来源:
                    family.setIncomesource(content);
                    break;
                default:
                    break;
            }

        }
    };



    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvRelation,tvReward;
        EditText etName,etUnit,etPosition,etEntry;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvRelation=itemView.findViewById(R.id.tv_relation);
            tvReward=itemView.findViewById(R.id.tv_reward);
            etName=itemView.findViewById(R.id.et_name);
            etUnit=itemView.findViewById(R.id.et_unit);
            etPosition=itemView.findViewById(R.id.et_position);
            etEntry=itemView.findViewById(R.id.et_entry);
        }
    }
}

