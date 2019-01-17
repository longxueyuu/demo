package com.lxy.domain;

/**
 * Created by lxy on 04/12/2017.
 */
public class GeneralRequestParam {

    private int userId;

    private String name;

    private boolean dispatch;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDispatch() {
        return dispatch;
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }
}
