package ru.sgnhp.domain;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowBean {
    private Long uid;
    private Long parentUid;
    private Long taskUid;
    private Long parentUserUid;
    private Long userUid;
    private String description;
    private String state;
    private String assignDate;
    private String finishDate;
    private TaskBean task;
    private WorkflowUserBean assignee;
    private WorkflowUserBean receiver;

    public WorkflowBean() {
    }

    public WorkflowBean(Long uid, Long parentUid, Long taskUid, Long parentUserUid, Long userUid, String description, String state, String assignDate, String finishDate, TaskBean task, WorkflowUserBean assignee, WorkflowUserBean receiver) {
        this.uid = uid;
        this.parentUid = parentUid;
        this.taskUid = taskUid;
        this.parentUserUid = parentUserUid;
        this.userUid = userUid;
        this.description = description;
        this.state = state;
        this.assignDate = assignDate;
        this.finishDate = finishDate;
        this.task = task;
        this.assignee = assignee;
        this.receiver = receiver;
    }

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

    public TaskBean getTask() {
        return task;
    }

    public void setTask(TaskBean task) {
        this.task = task;
    }

    public WorkflowUserBean getAssignee() {
        return assignee;
    }

    public void setAssignee(WorkflowUserBean assignee) {
        this.assignee = assignee;
    }

    public WorkflowUserBean getReceiver() {
        return receiver;
    }

    public void setReceiver(WorkflowUserBean receiver) {
        this.receiver = receiver;
    }

    public Long getParentUid() {
        return parentUid;
    }

    public void setParentUid(Long parentUid) {
        this.parentUid = parentUid;
    }
}
