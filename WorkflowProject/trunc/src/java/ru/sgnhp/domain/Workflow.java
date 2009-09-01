package ru.sgnhp.domain;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class Workflow {
    private Long uid;
    private Long parentUid;
    private Long taskUid;
    private Long parentUserUid;
    private Long userUid;
    private String description;
    private String state;
    private String assignDate;
    private String finishDate;
    private Task task;
    private WorkflowUser assignee;
    private WorkflowUser receiver;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public WorkflowUser getAssignee() {
        return assignee;
    }

    public void setAssignee(WorkflowUser assignee) {
        this.assignee = assignee;
    }

    public WorkflowUser getReceiver() {
        return receiver;
    }

    public void setReceiver(WorkflowUser receiver) {
        this.receiver = receiver;
    }

    public Long getParentUid() {
        return parentUid;
    }

    public void setParentUid(Long parentUid) {
        this.parentUid = parentUid;
    }
}
