package ru.sgnhp.domain;

public class SearchTaskBean {
    private String taskInternalNumber;
    private Boolean isTaskInternalNumber;
    private String taskDescription;
    private Boolean isTaskDescription;

    public String getTaskInternalNumber() {
        return taskInternalNumber;
    }

    public void setTaskInternalNumber(String TaskInternalNumber) {
        this.taskInternalNumber = TaskInternalNumber;
    }

    public Boolean getIsTaskInternalNumber() {
        return isTaskInternalNumber;
    }

    public void setIsTaskInternalNumber(Boolean isTaskInternalNumber) {
        this.isTaskInternalNumber = isTaskInternalNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String TaskDescription) {
        this.taskDescription = TaskDescription;
    }

    public Boolean getIsTaskDescription() {
        return isTaskDescription;
    }

    public void setIsTaskDescription(Boolean isTaskDescription) {
        this.isTaskDescription = isTaskDescription;
    }
}
