package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class HistoryBankBean extends BaseBean {

    private List<BankBaseBean.BankBase> data;

    public List<BankBaseBean.BankBase> getData() {
        return data;
    }

    public void setData(List<BankBaseBean.BankBase> data) {
        this.data = data;
    }
}
