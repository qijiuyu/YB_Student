package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class MyBankP {

    private Activity activity;
    private Face face;

    public MyBankP(Activity activity,Face face){
        this.activity=activity;
        this.face=face;
    }

    /**
     * 获取银行卡基本信息
     */
    public void getbankinfo(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getbankinfo(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BankBaseBean bankBaseBean= (BankBaseBean) object;
                if(bankBaseBean==null){
                    return;
                }
                if(bankBaseBean.isSussess()){

                    face.getbankinfo(bankBaseBean.getData());

                }else if(bankBaseBean.getCode()==-201){

                    face.getbankinfo(null);
                    ToastUtil.showLong(bankBaseBean.getDesc());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 获取银行卡历史信息
     */
    public void getBankHistory(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getBankHistory(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BankBaseBean bankBaseBean= (BankBaseBean) object;
                if(bankBaseBean==null){
                    return;
                }
                if(bankBaseBean.isSussess()){

                    face.getBankHistory(bankBaseBean.getData());

                }else{
                    ToastUtil.showLong(bankBaseBean.getDesc());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }



    /**
     * 收款信息
     */
    public void getCollMoneyList(){
        HttpMethod.getCollMoneyList(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final CollMoneyBean moneyBean= (CollMoneyBean) object;
                if(moneyBean==null){
                    return;
                }
                if(moneyBean.isSussess()){
                    face.getCollMoneyList(moneyBean.getData());
                }else{
                    ToastUtil.showLong(moneyBean.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



    public interface Face{

        void getbankinfo(BankBaseBean.BankBase bankBase);

        void getBankHistory(BankBaseBean.BankBase bankBase);

        void getCollMoneyList(List<CollMoneyBean.CollMoney> list);
    }
}
