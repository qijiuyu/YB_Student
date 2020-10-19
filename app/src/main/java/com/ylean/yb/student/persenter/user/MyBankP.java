package com.ylean.yb.student.persenter.user;

import android.app.Activity;

import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.BankProgress;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.bean.HistoryBankBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.util.List;

public class MyBankP {

    private Activity activity;
    private Face face;
    private Face2 face2;
    private Face3 face3;

    public MyBankP(Activity activity){
        this.activity=activity;
    }

    public void setFace(Face face){
        this.face=face;
    }

    public void setFace2(Face2 face2){
        this.face2=face2;
    }

    public void setFace3(Face3 face3){
        this.face3=face3;
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
                final HistoryBankBean historyBankBean= (HistoryBankBean) object;
                if(historyBankBean==null){
                    return;
                }
                if(historyBankBean.isSussess()){

                    face.getBankHistory(historyBankBean.getData());

                }else{
                    ToastUtil.showLong(historyBankBean.getDesc());
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


    /**
     * 获取银行卡办里进度
     */
    public void getBankProgress(){
        DialogUtil.showProgress(activity,"数据加载中");
        HttpMethod.getBankProgress(new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BankProgress bankProgress= (BankProgress) object;
                if(bankProgress==null){
                    return;
                }
                if(bankProgress.isSussess()){

                    face2.getBankProgress(bankProgress.getData());

                }else{
                    ToastUtil.showLong(bankProgress.getDesc());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    /**
     * 验证银行卡
     */
    public void verBank(String num){
        DialogUtil.showProgress(activity,"验证中");
        HttpMethod.verBank(num, new NetCallBack() {
            @Override
            public void onSuccess(Object object) {
                final BaseBean baseBean= (BaseBean) object;
                if(baseBean==null){
                    return;
                }
                if(baseBean.isSussess()){

                    face3.verBankSuccess();

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

        void getbankinfo(BankBaseBean.BankBase bankBase);

        void getBankHistory(List<BankBaseBean.BankBase> list);

        void getCollMoneyList(List<CollMoneyBean.CollMoney> list);
    }


    public interface Face2{
        void getBankProgress(List<BankProgress.Progress> list);
    }


    public interface Face3{
        void verBankSuccess();
    }
}
