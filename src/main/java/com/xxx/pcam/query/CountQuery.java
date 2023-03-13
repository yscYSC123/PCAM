package com.xxx.pcam.query;

import com.xxx.pcam.base.BaseQuery;

public class CountQuery extends BaseQuery {
    private Integer userCount;
    private Integer doctorCount;
    private Integer clientCount;

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }
}
