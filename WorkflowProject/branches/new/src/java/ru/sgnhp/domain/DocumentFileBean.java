package ru.sgnhp.domain;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "document_files", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "DocumentFileBean.findAll", query = "SELECT d FROM DocumentFileBean d"),
    @NamedQuery(name = "DocumentFileBean.findByUid", query = "SELECT d FROM DocumentFileBean d WHERE d.uid = :uid"),
    @NamedQuery(name = "DocumentFileBean.findByFileName", query = "SELECT d FROM DocumentFileBean d WHERE d.fileName = :fileName")})
public class DocumentFileBean implements Serializable {
    private static final long serialVersionUID = 11L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;
    @Basic(optional = false, fetch=FetchType.LAZY)
    @Lob
    @Column(name = "BlobField", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] blobField;
    @ForeignKey(name = "fk_document_document_files")
    @JoinColumn(name = "DocumentUid", referencedColumnName = "Uid",  nullable = false, columnDefinition = "INTEGER(11)")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentBean documentBean;

    public DocumentFileBean() {
    }

    public DocumentFileBean(Long uid) {
        this.uid = uid;
    }

    public DocumentFileBean(Long uid, String fileName, byte[] blobField) {
        this.uid = uid;
        this.fileName = fileName;
        this.blobField = blobField;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBlobField() {
        return blobField;
    }

    public void setBlobField(byte[] blobField) {
        this.blobField = blobField;
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
        if (!(object instanceof DocumentFileBean)) {
            return false;
        }
        DocumentFileBean other = (DocumentFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.DocumentFileBean[uid=" + uid + "]";
    }

    public DocumentBean getDocumentBean() {
        return documentBean;
    }

    public void setDocumentBean(DocumentBean documentBean) {
        this.documentBean = documentBean;
    }

}
