package com.ylean.yb.student.persenter;

import android.app.Activity;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class FamilyP {

    private Activity activity;
    private Face face;
    private Face2 face2;

    public FamilyP(Activity activity, Face face){
        this.activity=activity;
        this.face=face;
    }

    public FamilyP(Activity activity, Face2 face2){
        this.activity=activity;
        this.face2=face2;
    }


    /**
     * 查询家庭成员
     */
    public void getFamilyList(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getFamilyList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final FamilyBean familyBean= (FamilyBean) object;
                if(familyBean.isSussess()){

                    face.getFamily(familyBean.getData());

                }else{
                    ToastUtil.showLong(familyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 添加家庭成员
     */
    public void addFamily(String familymembers){
        DialogUtil.showProgress(activity,"添加中");
        HttpMethod.addFamily(familymembers, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.addFamily();

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 删除家庭成员
     */
    public void deleteFamily(final FamilyBean.ListBean listBean){
        DialogUtil.showProgress(activity,"删除中");
        HttpMethod.deleteFamily(listBean.getId(), new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face.deleteSuccess(listBean);

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 修改家庭成员
     */
    public void updateFamily(int id,String company,String incomesource,String name,String occupation,int relation,String relationname,int whethersupport){
        DialogUtil.showProgress(activity,"修改中");
        HttpMethod.updateFamily(id, company, incomesource, name, occupation, relation, relationname, whethersupport, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){

                    face2.updateFamily();

                }else{
                    ToastUtil.showLong(baseBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    public interface Face{

        void getFamily(List<FamilyBean.ListBean> list);

        void deleteSuccess(FamilyBean.ListBean listBean);
    }


    public interface Face2{
        void addFamily();

        void updateFamily();
    }
}
