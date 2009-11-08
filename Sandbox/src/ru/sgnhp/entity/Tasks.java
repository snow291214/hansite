/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author alexey
 */
@Entity
@Table(name = "tasks", catalog = "workflowdb", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({@NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"), @NamedQuery(name = "Tasks.findByUid", query = "SELECT t FROM Tasks t WHERE t.uid = :uid"), @NamedQuery(name = "Tasks.findByInternalNumber", query = "SELECT t FROM Tasks t WHERE t.internalNumber = :internalNumber"), @NamedQuery(name = "Tasks.findByExternalNumber", query = "SELECT t FROM Tasks t WHERE t.externalNumber = :externalNumber"), @NamedQuery(name = "Tasks.findByDescription", query = "SELECT t FROM Tasks t WHERE t.description = :description"), @NamedQuery(name = "Tasks.findByStartDate", query = "SELECT t FROM Tasks t WHERE t.startDate = :startDate"), @NamedQuery(name = "Tasks.findByDueDate", query = "SELECT t FROM Tasks t WHERE t.dueDate = :dueDate")})
public class Tasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Column(name = "InternalNumber", length = 20)
    private String internalNumber;
    @Column(name = "ExternalNumber", length = 20)
    private String externalNumber;
    @Column(name = "Description", length = 250)
    private String description;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @OneToMany(mappedBy = "taskUid", fetch = FetchType.LAZY)
    private Collection<Files> filesCollection;

    public Tasks() {
    }

    public Tasks(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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

    public Collection<Files> getFilesCollection() {
        return filesCollection;
    }

    public void setFilesCollection(Collection<Files> filesCollection) {
        this.filesCollection = filesCollection;
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
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.entity.Tasks[uid=" + uid + "]";
    }

}
