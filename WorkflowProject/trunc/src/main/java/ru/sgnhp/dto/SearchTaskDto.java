package ru.sgnhp.dto;

import java.util.Date;

public class SearchTaskDto {
    private String taskInternalNumber;
    private String taskIncomingNumber;
    private String taskExternalNumber;
    private String assigneeName;
    private String externalCompany;
    private String taskDescription;
    private Date startDate;
    private Date finishDate;
    private int searchType;
    private Long receiverUid;
    private String primaveraUid;

    public String getTaskInternalNumber() {
        return taskInternalNumber;
    }

    public void setTaskInternalNumber(String TaskInternalNumber) {
        this.taskInternalNumber = TaskInternalNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String TaskDescription) {
        this.taskDescription = TaskDescription;
    }

    public String getTaskIncomingNumber() {
        return taskIncomingNumber;
    }

    public void setTaskIncomingNumber(String taskIncomingNumber) {
        this.taskIncomingNumber = taskIncomingNumber;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Long getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(Long receiverUid) {
        this.receiverUid = receiverUid;
    }

    public String getTaskExternalNumber() {
        return taskExternalNumber;
    }

    public void setTaskExternalNumber(String taskExternalNumber) {
        this.taskExternalNumber = taskExternalNumber;
    }

    public String getExternalCompany() {
        return externalCompany;
    }

    public void setExternalCompany(String externalCompany) {
        this.externalCompany = externalCompany;
    }

    public String getPrimaveraUid() {
        return primaveraUid;
    }

    public void setPrimaveraUid(String primaveraUid) {
        this.primaveraUid = primaveraUid;
    }
}
