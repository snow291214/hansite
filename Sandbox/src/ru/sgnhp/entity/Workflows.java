package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@NamedQueries({@NamedQuery(name = "Workflows.findAll", query = "SELECT w FROM Workflows w"), @NamedQuery(name = "Workflows.findByUid", query = "SELECT w FROM Workflows w WHERE w.uid = :uid"), @NamedQuery(name = "Workflows.findByParentUid", query = "SELECT w FROM Workflows w WHERE w.parentUid = :parentUid"), @NamedQuery(name = "Workflows.findByParentUserUid", query = "SELECT w FROM Workflows w WHERE w.parentUserUid = :parentUserUid"), @NamedQuery(name = "Workflows.findByUserUid", query = "SELECT w FROM Workflows w WHERE w.userUid = :userUid"), @NamedQuery(name = "Workflows.findByDescription", query = "SELECT w FROM Workflows w WHERE w.description = :description"), @NamedQuery(name = "Workflows.findByState", query = "SELECT w FROM Workflows w WHERE w.state = :state"), @NamedQuery(name = "Workflows.findByAssignDate", query = "SELECT w FROM Workflows w WHERE w.assignDate = :assignDate"), @NamedQuery(name = "Workflows.findByFinishDate", query = "SELECT w FROM Workflows w WHERE w.finishDate = :finishDate")})
public class Workflows implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "ParentUid", nullable = false)
    private int parentUid;
    @Basic(optional = false)
    @Column(name = "ParentUserUid", nullable = false)
    private int parentUserUid;
    @Basic(optional = false)
    @Column(name = "UserUid", nullable = false)
    private int userUid;
    @Column(name = "Description", length = 255)
    private String description;
    @Basic(optional = false)
    @Column(name = "State", nullable = false)
    private short state;
    @Column(name = "AssignDate")
    @Temporal(TemporalType.DATE)
    private Date assignDate;
    @Column(name = "FinishDate")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @ForeignKey(name="workflows_fk")
    @JoinColumn(name = "TaskUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)

    private Tasks taskUid;

    public Workflows() {
    }

    public Workflows(Long uid) {
        this.uid = uid;
    }

    public Workflows(Long uid, int parentUid, int parentUserUid, int userUid, short state) {
        this.uid = uid;
        this.parentUid = parentUid;
        this.parentUserUid = parentUserUid;
        this.userUid = userUid;
        this.state = state;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public int getParentUid() {
        return parentUid;
    }

    public void setParentUid(int parentUid) {
        this.parentUid = parentUid;
    }

    public int getParentUserUid() {
        return parentUserUid;
    }

    public void setParentUserUid(int parentUserUid) {
        this.parentUserUid = parentUserUid;
    }

    public int getUserUid() {
        return userUid;
    }

    public void setUserUid(int userUid) {
        this.userUid = userUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
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

    public Tasks getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(Tasks taskUid) {
        this.taskUid = taskUid;
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
        if (!(object instanceof Workflows)) {
            return false;
        }
        Workflows other = (Workflows) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.dao.impl.Workflows[uid=" + uid + "]";
    }

}
