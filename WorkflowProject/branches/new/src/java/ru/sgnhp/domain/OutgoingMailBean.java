package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "outgoingmail", catalog = "workflowdb", schema = "")
@NamedQueries({@NamedQuery(name = "OutgoingMailBean.findAll", query = "SELECT o FROM OutgoingMailBean o"),
@NamedQuery(name = "OutgoingMailBean.findByUid", query = "SELECT o FROM OutgoingMailBean o WHERE o.uid = :uid"),
@NamedQuery(name = "OutgoingMailBean.findByOutgoingNumber", query = "SELECT o FROM OutgoingMailBean o WHERE o.outgoingNumber = :outgoingNumber"),
@NamedQuery(name = "OutgoingMailBean.findByOutgoingDate", query = "SELECT o FROM OutgoingMailBean o WHERE o.outgoingDate = :outgoingDate"),
@NamedQuery(name = "OutgoingMailBean.findByDocumentumNumber", query = "SELECT o FROM OutgoingMailBean o WHERE o.documentumNumber = :documentumNumber"),
@NamedQuery(name = "OutgoingMailBean.findByReceiverCompany", query = "SELECT o FROM OutgoingMailBean o WHERE o.receiverCompany = :receiverCompany"),
@NamedQuery(name = "OutgoingMailBean.findByReceiverName", query = "SELECT o FROM OutgoingMailBean o WHERE o.receiverName = :receiverName"),
@NamedQuery(name = "OutgoingMailBean.findByResponsibleName", query = "SELECT o FROM OutgoingMailBean o WHERE o.responsibleName = :responsibleName"),
@NamedQuery(name = "OutgoingMailBean.findByDueDate", query = "SELECT o FROM OutgoingMailBean o WHERE o.dueDate = :dueDate")})
public class OutgoingMailBean implements Serializable {
    private static final long serialVersionUID = 7L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "OutgoingNumber", nullable = false)
    private Long outgoingNumber;
    @Column(name = "DocumentumNumber", length = 50)
    private String documentumNumber;
    @Basic(optional = false)
    @Lob
    @Column(name = "Description", nullable = false, length = 65535)
    private String description;
    @Basic(optional = false)
    @Column(name = "OutgoingDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date outgoingDate;
    @Column(name = "ReceiverCompany", length = 255)
    private String receiverCompany;
    @Column(name = "ReceiverName", length = 150)
    private String receiverName;
    @Column(name = "ResponsibleName", length = 50)
    private String responsibleName;
    @Column(name = "DueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outgoingMailBean", fetch = FetchType.LAZY)
    private Set<OutgoingFileBean> outgoingFileBeanSet = new HashSet<OutgoingFileBean>();

    public OutgoingMailBean() {
    }

    public OutgoingMailBean(Long uid) {
        this.uid = uid;
    }

    public OutgoingMailBean(Long uid, String description, Long outgoingNumber, Date outgoingDate) {
        this.uid = uid;
        this.description = description;
        this.outgoingNumber = outgoingNumber;
        this.outgoingDate = outgoingDate;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOutgoingNumber() {
        return outgoingNumber;
    }

    public void setOutgoingNumber(Long outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public Date getOutgoingDate() {
        return outgoingDate;
    }

    public void setOutgoingDate(Date outgoingDate) {
        this.outgoingDate = outgoingDate;
    }

    public String getDocumentumNumber() {
        return documentumNumber;
    }

    public void setDocumentumNumber(String documentumNumber) {
        this.documentumNumber = documentumNumber;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Set<OutgoingFileBean> getOutgoingFileBeanSet() {
        return outgoingFileBeanSet;
    }

    public void setOutgoingFileBeanSet(Set<OutgoingFileBean> outgoingFileBeanSet) {
        this.outgoingFileBeanSet = outgoingFileBeanSet;
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
        if (!(object instanceof OutgoingMailBean)) {
            return false;
        }
        OutgoingMailBean other = (OutgoingMailBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.OutgoingMailBean[uid=" + uid + "]";
    }

}