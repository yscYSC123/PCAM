package com.xxx.pcam.query;

import com.xxx.pcam.base.BaseQuery;

public class PgtestQuery extends BaseQuery {

    private  String userName;
    private String result;
    private String userOp;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserOp() {
        return userOp;
    }

    public void setUserOp(String userOp) {
        this.userOp = userOp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
