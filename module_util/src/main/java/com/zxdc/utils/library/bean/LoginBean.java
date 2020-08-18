package com.zxdc.utils.library.bean;

public class LoginBean extends BaseBean {

    private String token;

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
