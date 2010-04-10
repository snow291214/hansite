package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.ForeignKey;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "workflows", catalog = "workflowdb", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({
    @NamedQuery(name = "WorkflowBean.findAll", query = "SELECT w FROM WorkflowBean w ORDER BY w.uid"),
    @NamedQuery(name = "WorkflowBean.findAllParentWorkflowsByParentUserUid",
    query = "SELECT w FROM WorkflowBean w Where w.parentUid = -1 And w.assignee.uid = :parentUserUid " +
    "And w.stateBean.stateUid <> 3 ORDER BY w.uid"),
    @NamedQuery(name = "WorkflowBean.findAllUncompletedByParentUserUid",
    query = "SELECT w FROM WorkflowBean w Where w.assignee.uid = :parentUserUid " +
    "And w.stateBean.stateUid in (0,2) ORDER BY w.uid"),
    @NamedQuery(name = "WorkflowBean.findByUid", query = "SELECT w FROM WorkflowBean w WHERE w.uid = :uid"),
    @NamedQuery(name = "WorkflowBean.findByParentUid", query = "SELECT w FROM WorkflowBean w WHERE w.parentUid = :parentUid"),
    @NamedQuery(name = "WorkflowBean.findByAssignDate", query = "SELECT w FROM WorkflowBean w WHERE w.assignDate = :assignDate"),
    @NamedQuery(name = "WorkflowBean.findByFinishDate", query = "SELECT w FROM WorkflowBean w WHERE w.finishDate = :finishDate"),
    @NamedQuery(name = "WorkflowBean.findRecievedByUserUid", query = "SELECT w FROM WorkflowBean w WHERE w.receiver.uid = :userUid and w.stateBean.stateUid in (0,2) order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findReceived", query = "SELECT w FROM WorkflowBean w WHERE w.stateBean.stateUid in (0,2) order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findAssignedByUserUid", query = "SELECT w FROM WorkflowBean w WHERE w.assignee.uid = :userUid and w.stateBean.stateUid <> 3 order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findAssignedAndCompletedByUserUid", query = "SELECT w FROM WorkflowBean w WHERE w.assignee.uid = :userUid and w.stateBean.stateUid = 3  order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findCompletedByUserUid", query = "SELECT w FROM WorkflowBean w WHERE w.receiver.uid = :userUid and w.stateBean.stateUid = 3 order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findByDescription", query = "SELECT w FROM WorkflowBean w WHERE w.receiver.uid = :userUid and w.description like :description order by w.uid desc"),
    @NamedQuery(name = "WorkflowBean.findByTaskUid", query = "SELECT w FROM WorkflowBean w WHERE w.taskBean.uid = :taskUid and w.parentUid = -1"),
    @NamedQuery(name = "WorkflowBean.updateWorkflowState", query = "UPDATE WorkflowBean w SET w.stateBean = :stateBean WHERE w.uid = :uid")
})
public class WorkflowBean implements Serializable {

    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "ParentUid", nullable = false)
    private Long parentUid;
    @Basic(optional = false)
    @ForeignKey(name = "fk_workflows_users_parent")
    @JoinColumn(name = "ParentUserUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false)
    private WorkflowUserBean assignee;
    @ForeignKey(name = "fk_workflows_users")
    @JoinColumn(name = "UserUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false)
    private WorkflowUserBean receiver;
    @Lob
    @Column(name = "Description", length = 65535)
    private String description;
    @ForeignKey(name = "fk_workflows_state")
    @JoinColumn(name = "State", referencedColumnName = "StateUid", nullable = false)
    @ManyToOne(optional = false)
    private StateBean stateBean;
    @Column(name = "AssignDate")
    @Temporal(TemporalType.DATE)
    private Date assignDate;
    @Column(name = "FinishDate")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Lob
    @Column(name = "WorkflowNote", length = 65535)
    private String workflowNote;
    @ForeignKey(name = "fk_workflows_tasks")
    @JoinColumn(name = "TaskUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false)
    private TaskBean taskBean;

    public WorkflowBean() {
    }

    public WorkflowBean(Long uid) {
        this.uid = uid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateBean getState() {
        return stateBean;
    }

    public void setState(StateBean state) {
        this.stateBean = state;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkflowBean)) {
            return false;
        }
        WorkflowBean other = (WorkflowBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.WorkflowBean[uid=" + uid + "]";
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

    public TaskBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(TaskBean taskBean) {
        this.taskBean = taskBean;
    }
}