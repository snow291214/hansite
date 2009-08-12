package ru.sgnhp.domain;

import java.sql.Date;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class Workflow {
    private Long uid;
    private Long taskUid;
    private Long parentUserUid;
    private Long userUid;
    private String description;
    private Short state;
    private String assignDate;
    private String finishDate;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(Long taskUid) {
        this.taskUid = taskUid;
    }

    public Long getParentUserUid() {
        return parentUserUid;
    }

    public void setParentUserUid(Long parentUserUid) {
        this.parentUserUid = parentUserUid;
    }

    public Long getUserUid() {
        return userUid;
    }

    public void setUserUid(Long userUid) {
        this.userUid = userUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
