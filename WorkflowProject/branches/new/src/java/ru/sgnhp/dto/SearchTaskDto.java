package ru.sgnhp.dto;

import java.util.Date;

public class SearchTaskDto {
    private String taskInternalNumber;
    private String taskIncomingNumber;
    private String assigneeName;
    private String taskDescription;
    private Date startDate;
    private Date finishDate;
    private int searchType;

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
}
