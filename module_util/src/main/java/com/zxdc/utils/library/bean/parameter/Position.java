package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class Position implements Serializable {

    private int iLevel=1;

    public int getiLevel() {
        return iLevel;
    }

    public void setiLevel(int iLevel) {
        this.iLevel = iLevel;
    }
}
