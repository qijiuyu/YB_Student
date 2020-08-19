package com.ylean.yb.student.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.callback.SelectCallBack;
import com.ylean.yb.student.callback.SelectRelationCallBack;
import com.ylean.yb.student.persenter.FamilyP;
import com.zxdc.utils.library.bean.AddFamily;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddFamilyView extends Dialog implements FamilyP.Face2 {

    @BindView(R.id.tv_relation)
    TextView tvRelation;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_unit)
    EditText etUnit;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.et_entry)
    EditText etEntry;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    private Activity context;
    //要编辑的家庭对象
    private FamilyBean.ListBean listBean;
    private SelectCallBack selectCallBack;
    private FamilyP familyP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_family);
        // 绑定初始化ButterKnife
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = context.getResources().getDisplayMetrics().widthPixels; // 宽度
        initView();
    }

    public AddFamilyView(Activity context,FamilyBean.ListBean listBean,SelectCallBack selectCallBack) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        this.listBean=listBean;
        this.selectCallBack=selectCallBack;
    }

    /**
     * 初始化
     */
    private void initView() {
        familyP=new FamilyP(context,this);
        if(listBean!=null){
            switch (listBean.getRelation()){
                case 1:
                    tvRelation.setText("父亲");
                    break;
                case 2:
                    tvRelation.setText("母亲");
                    break;
                case 3:
                    tvRelation.setText("哥哥");
                    break;
                case 4:
                    tvRelation.setText("姐姐");
                    break;
                case 5:
                    tvRelation.setText("弟弟");
                    break;
                case 6:
                    tvRelation.setText("妹妹");
                    break;
                case 7:
                    tvRelation.setText(listBean.getRelationname());
                    break;
                default:
                    break;
            }
            tvRelation.setTag(listBean.getRelation());
            etName.setText(listBean.getName());
            etUnit.setText(listBean.getCompany());
            etPosition.setText(listBean.getOccupation());
            etEntry.setText(listBean.getIncomesource());
            if(listBean.getWhethersupport()==0){
                tvReward.setText("否");
            }else{
                tvReward.setText("是");
            }
            tvReward.setTag(listBean.getWhethersupport());
        }
    }


    @OnClick({R.id.tv_relation, R.id.tv_reward, R.id.tv_add,R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_relation:
                new SelectRelation(context, new SelectRelationCallBack() {
                    public void onSuccess(Object object, Object object2) {
                        tvRelation.setText((String)object);
                        tvRelation.setTag(object2);
                    }
                }).show();
                break;
            case R.id.tv_reward:
                new SelectReward(context, new SelectRelationCallBack() {
                    public void onSuccess(Object object, Object object2) {
                        tvReward.setText((String)object);
                        tvReward.setTag(object2);
                    }
                }).show();
                break;
            case R.id.tv_add:
                final String relation=tvRelation.getText().toString().trim();
                final String name=etName.getText().toString().trim();
                final String unit=etUnit.getText().toString().trim();
                final String position=etPosition.getText().toString().trim();
                final String entry=etEntry.getText().toString().trim();
                if(TextUtils.isEmpty(relation)){
                    ToastUtil.showLong("请选择与本人关系");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请输入姓名");
                    return;
                }
                if(TextUtils.isEmpty(unit)){
                    ToastUtil.showLong("请输入单位");
                    return;
                }
                if(TextUtils.isEmpty(position)){
                    ToastUtil.showLong("请输入职位");
                    return;
                }
                if(TextUtils.isEmpty(entry)){
                    ToastUtil.showLong("请输入主要收入来源");
                    return;
                }

                /**
                 * 添加
                 */
                if(listBean==null){
                    AddFamily addFamily=new AddFamily();
                    addFamily.setRelation((int)tvRelation.getTag());
                    if(addFamily.getRelation()==7){
                        addFamily.setRelationname(relation);
                    }
                    addFamily.setName(name);
                    addFamily.setCompany(unit);
                    addFamily.setOccupation(position);
                    addFamily.setIncomesource(entry);
                    addFamily.setWhethersupport(Integer.parseInt(tvReward.getTag().toString()));
                    List<AddFamily> list=new ArrayList<>();
                    list.add(addFamily);
                    familyP.addFamily(JsonUtil.objectToString(list));
                }

                /**
                 * 修改
                 */
                if(listBean!=null){
                    final int relationCode=(int)tvRelation.getTag();
                    familyP.updateFamily(listBean.getId(),unit,entry,name,position,relationCode,relationCode==7 ? relation : "",(int)tvReward.getTag());
                }
                break;
            case R.id.rel:
                 dismiss();
                 break;
            default:
                break;
        }
    }


    /**
     * 添加成功
     */
    @Override
    public void addFamily() {
        selectCallBack.selectBack(null);
        dismiss();
    }


    /**
     * 修改成功
     */
    @Override
    public void updateFamily() {
        selectCallBack.selectBack(null);
        dismiss();
    }
}
