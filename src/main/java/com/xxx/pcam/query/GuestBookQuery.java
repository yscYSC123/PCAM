package com.xxx.pcam.query;

import com.xxx.pcam.base.BaseQuery;

public class GuestBookQuery extends BaseQuery {

    private String creater;
    private String context;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
