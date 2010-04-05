package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "tasks", catalog = "workflowdb", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({
    @NamedQuery(name = "TaskBean.findAll", query = "SELECT t FROM TaskBean t"),
    @NamedQuery(name = "TaskBean.findByUid", query = "SELECT t FROM TaskBean t WHERE t.uid = :uid"),
    @NamedQuery(name = "TaskBean.findByInternalNumber", query = "SELECT t FROM TaskBean t WHERE t.internalNumber = :internalNumber"),
    @NamedQuery(name = "TaskBean.findByIncomingNumber", query = "SELECT t FROM TaskBean t WHERE t.incomingNumber = :incomingNumber"),
    @NamedQuery(name = "TaskBean.findByExternalNumber", query = "SELECT t FROM TaskBean t WHERE t.externalNumber = :externalNumber"),
    @NamedQuery(name = "TaskBean.findByExternalCompany", query = "SELECT t FROM TaskBean t WHERE t.externalCompany like :externalCompany"),
    @NamedQuery(name = "TaskBean.findByExternalAssignee", query = "SELECT t FROM TaskBean t WHERE t.externalAssignee like :externalAssignee"),
    @NamedQuery(name = "TaskBean.findByStartDate", query = "SELECT t FROM TaskBean t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TaskBean.findByDueDate", query = "SELECT t FROM TaskBean t WHERE t.dueDate = :dueDate"),
    @NamedQuery(name = "TaskBean.findByDescription", query = "SELECT t FROM TaskBean t WHERE t.description like :description")
})
public class TaskBean implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "InternalNumber")
    private Integer internalNumber;
    @Column(name = "IncomingNumber")
    private Integer incomingNumber;
    @Column(name = "ExternalNumber", length = 20)
    private String externalNumber;
    @Column(name = "ExternalCompany", length = 150)
    private String externalCompany;
    @Column(name = "ExternalAssignee", length = 150)
    private String externalAssignee;
    @Lob
    @Column(name = "Description", length = 65535)
    private String description;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @OneToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FileBean> filesSet = new HashSet<FileBean>();

    public TaskBean() {
    }

    public TaskBean(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(Integer internalNumber) {
        this.internalNumber = internalNumber;
    }

    public Integer getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(Integer incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public String getExternalCompany() {
        return externalCompany;
    }

    public void setExternalCompany(String externalCompany) {
        this.externalCompany = externalCompany;
    }

    public String getExternalAssignee() {
        return externalAssignee;
    }

    public void setExternalAssignee(String externalAssignee) {
        this.externalAssignee = externalAssignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Set<FileBean> getFilesSet() {
        return filesSet;
    }

    public void setFilesSet(Set<FileBean> filesSet) {
        this.filesSet = filesSet;
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
        if (!(object instanceof TaskBean)) {
            return false;
        }
        TaskBean other = (TaskBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.TaskBean[uid=" + uid + "]";
    }
}