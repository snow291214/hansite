package ru.sgnhp.domain;

public class SearchTaskBean {
    private String TaskInternalNumber;
    private Boolean isTaskInternalNumber;
    private String TaskDescription;
    private Boolean isTaskDescription;

    public String getTaskInternalNumber() {
        return TaskInternalNumber;
    }

    public void setTaskInternalNumber(String TaskInternalNumber) {
        this.TaskInternalNumber = TaskInternalNumber;
    }

    public Boolean getIsTaskInternalNumber() {
        return isTaskInternalNumber;
    }

    public void setIsTaskInternalNumber(Boolean isTaskInternalNumber) {
        this.isTaskInternalNumber = isTaskInternalNumber;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String TaskDescription) {
        this.TaskDescription = TaskDescription;
    }

    public Boolean getIsTaskDescription() {
        return isTaskDescription;
    }

    public void setIsTaskDescription(Boolean isTaskDescription) {
        this.isTaskDescription = isTaskDescription;
    }
}
