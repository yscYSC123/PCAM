package com.xxx.pcam.query;

import com.xxx.pcam.base.BaseQuery;

public class BookingQuery extends BaseQuery {

    private Integer doctorId;   //咨询师id

    private Integer clientId;      //来访者id

    private String doctorName;      //咨询师姓名

    private String clientName;      //来访者姓名

    private Integer status;     //状态

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
