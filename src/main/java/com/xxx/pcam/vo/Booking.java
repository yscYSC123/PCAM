package com.xxx.pcam.vo;

import java.util.Date;

public class Booking {
    private Integer archivesId;

    private Integer clientId;

    private Integer doctorId;

    private String clientDescription;

    private Date applyTime;

    private String expectPlace;

    private String expectTime;

    private Date startDatetime;

    private Date endDatetime;

    private String subPlace;

    private Integer status;

    private String docPath;

    private String evaluation;

    private Integer evaDo;

    public Integer getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Integer archivesId) {
        this.archivesId = archivesId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription == null ? null : clientDescription.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getExpectPlace() {
        return expectPlace;
    }

    public void setExpectPlace(String expectPlace) {
        this.expectPlace = expectPlace == null ? null : expectPlace.trim();
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime == null ? null : expectTime.trim();
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getSubPlace() {
        return subPlace;
    }

    public void setSubPlace(String subPlace) {
        this.subPlace = subPlace == null ? null : subPlace.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath == null ? null : docPath.trim();
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    public Integer getEvaDo() {
        return evaDo;
    }

    public void setEvaDo(Integer evaDo) {
        this.evaDo = evaDo;
    }
}