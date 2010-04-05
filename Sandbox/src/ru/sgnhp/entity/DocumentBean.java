package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;
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
@Table(name = "documents", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "DocumentBean.findAll", query = "SELECT d FROM DocumentBean d"),
    @NamedQuery(name = "DocumentBean.findByUid", query = "SELECT d FROM DocumentBean " +
        "d WHERE d.uid = :uid"),
    @NamedQuery(name = "DocumentBean.findByDocumentNumber", query = "SELECT d FROM " +
        "DocumentBean d WHERE d.documentNumber = :documentNumber and " +
        "d.documentTypeBean = :documentTypeBean"),
    @NamedQuery(name = "DocumentBean.findByDocumentDate", query = "SELECT d FROM " +
        "DocumentBean d WHERE d.documentDate between :startDate and :finishDate and " +
        "d.documentTypeBean = :documentTypeBean")})
public class DocumentBean implements Serializable {
    private static final long serialVersionUID = 10L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "DocumentNumber", nullable = false)
    private int documentNumber;
    @Basic(optional = false)
    @Column(name = "DocumentDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "Description", nullable = false, length = 65535)
    private String description;
    @ForeignKey(name = "fk_document_document_types")
    @JoinColumn(name = "DocumentTypeUid", referencedColumnName = "Uid",  nullable = false, columnDefinition = "INTEGER(11)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentTypeBean documentTypeBean;
    @ForeignKey(name = "fk_documents_contact_users")
    @JoinColumn(name = "ContactUserUid", referencedColumnName = "Uid",  nullable = false, columnDefinition = "INTEGER(11) UNSIGNED")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WorkflowUserBean contactPerson;
    @ForeignKey(name = "fk_documents_control_users")
    @JoinColumn(name = "ControlUserUid", referencedColumnName = "Uid",  nullable = false, columnDefinition = "INTEGER(11) UNSIGNED")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WorkflowUserBean controlPerson;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentBean", fetch = FetchType.LAZY)
    private Set<DocumentFileBean> documentFileBeanSet;

    public DocumentBean() {
    }

    public DocumentBean(Long uid) {
        this.uid = uid;
    }

    public DocumentBean(Long uid, int documentNumber, Date documentDate, String description) {
        this.uid = uid;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.description = description;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DocumentFileBean> getDocumentFileBeanSet() {
        return documentFileBeanSet;
    }

    public void setDocumentFileBeanSet(Set<DocumentFileBean> documentFileBeanSet) {
        this.documentFileBeanSet = documentFileBeanSet;
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
        if (!(object instanceof DocumentBean)) {
            return false;
        }
        DocumentBean other = (DocumentBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.DocumentBean[uid=" + uid + "]";
    }

    public DocumentTypeBean getDocumentTypeBean() {
        return documentTypeBean;
    }

    public void setDocumentTypeBean(DocumentTypeBean documentTypeBean) {
        this.documentTypeBean = documentTypeBean;
    }

    public WorkflowUserBean getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(WorkflowUserBean contactPerson) {
        this.contactPerson = contactPerson;
    }

    public WorkflowUserBean getControlPerson() {
        return controlPerson;
    }

    public void setControlPerson(WorkflowUserBean controlPerson) {
        this.controlPerson = controlPerson;
    }


}
