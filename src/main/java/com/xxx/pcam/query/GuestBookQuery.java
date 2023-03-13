package com.xxx.pcam.query;

import com.xxx.pcam.base.BaseQuery;

public class GuestBookQuery extends BaseQuery {

    private Integer createrId;
    private String context;

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
