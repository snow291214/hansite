package ru.sgnhp.domain;

import java.util.List;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class TaskBean {
    private Long uid;
    private String internalNumber;
    private String externalNumber;
    private String description;
    private String startDate;
    private String dueDate;
    private List<FileUploadBean> taskFiles;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<FileUploadBean> getTaskFiles() {
        return taskFiles;
    }

    public void setTaskFiles(List<FileUploadBean> taskFiles) {
        this.taskFiles = taskFiles;
    }
}