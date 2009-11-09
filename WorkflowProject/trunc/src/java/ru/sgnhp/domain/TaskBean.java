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
    private int internalNumber;
    private Long incomingNumber;
    private String externalNumber;
    private String externalCompany;
    private String externalAssignee;
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

    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
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

    public String getExternalAssignee() {
        return externalAssignee;
    }

    public void setExternalAssignee(String externalAssignee) {
        this.externalAssignee = externalAssignee;
    }

    public String getExternalCompany() {
        return externalCompany;
    }

    public void setExternalCompany(String externalCompany) {
        this.externalCompany = externalCompany;
    }

    public Long getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(Long incomingNumber) {
        this.incomingNumber = incomingNumber;
    }
}
