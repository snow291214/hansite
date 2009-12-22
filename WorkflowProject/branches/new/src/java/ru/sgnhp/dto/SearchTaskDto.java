package ru.sgnhp.dto;

public class SearchTaskDto {
    private String taskInternalNumber;
    private String taskIncomingNumber;
    private String assigneeName;
    private String taskDescription;
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
}
