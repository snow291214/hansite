package ru.sgnhp.dto;

import java.io.Serializable;
import java.util.Date;
import ru.sgnhp.domain.StateBean;
import ru.sgnhp.domain.TaskBean;
import ru.sgnhp.domain.WorkflowBean;
import ru.sgnhp.domain.WorkflowUserBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class WorkflowBeanDto implements Serializable {
    private static final long serialVersionUID = 5L;
    private Long uid;
    private Long parentUid;
    private WorkflowUserBean assignee;
    private WorkflowUserBean receiver;
    private String description;
    private StateBean stateBean;
    private Date assignDate;
    private Date finishDate;
    private String workflowNote;
    private TaskBean taskBean;
    private Long stateUid;
    private String[] userUids;

    public WorkflowBeanDto(WorkflowBean workflowBean) {
        this.uid = workflowBean.getUid();
        this.parentUid = workflowBean.getParentUid();
        this.assignee = null;
        this.receiver = null;
        this.description = workflowBean.getDescription();
        this.stateBean = workflowBean.getState();
        this.assignDate = workflowBean.getAssignDate();
        this.finishDate = workflowBean.getFinishDate();
        this.workflowNote = workflowBean.getWorkflowNote();
        this.taskBean = workflowBean.getTaskBean();
    }

    public WorkflowBeanDto() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getParentUid() {
        return parentUid;
    }

    public void setParentUid(Long parentUid) {
        this.parentUid = parentUid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateBean getStateBean() {
        return stateBean;
    }

    public void setStateBean(StateBean stateBean) {
        this.stateBean = stateBean;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getWorkflowNote() {
        return workflowNote;
    }

    public void setWorkflowNote(String workflowNote) {
        this.workflowNote = workflowNote;
    }

    public TaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(TaskBean taskBean) {
        this.taskBean = taskBean;
    }

    public Long getStateUid() {
        return stateUid;
    }

    public void setStateUid(Long stateUid) {
        this.stateUid = stateUid;
    }

    public String[] getUserUids() {
        return userUids;
    }

    public void setUserUids(String[] userUids) {
        this.userUids = userUids;
    }
}
