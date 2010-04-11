package ru.sgnhp.entity;

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
@Table(name = "outgoingfiles", catalog = "workflowdb", schema = "")
@NamedQueries({@NamedQuery(name = "OutgoingFileBean.findAll", query = "SELECT o FROM OutgoingFileBean o"),
@NamedQuery(name = "OutgoingFileBean.findByUid", query = "SELECT o FROM OutgoingFileBean o WHERE o.uid = :uid"),
@NamedQuery(name = "OutgoingFileBean.findByOutgoingMail", query = "SELECT o FROM OutgoingFileBean o WHERE o.outgoingMailBean = :outgoingMailBean"),
@NamedQuery(name = "OutgoingFileBean.findByFileName", query = "SELECT o FROM OutgoingFileBean o WHERE o.fileName = :fileName")})
public class OutgoingFileBean implements Serializable {

    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;
    @Column(name = "FilePath", length = 255, nullable=false)
    private String filePath;
    @Basic(optional = false)
    @Lob
    @Column(name = "BlobField", columnDefinition = "LONGBLOB")
    private byte[] blobField;
    @ForeignKey(name = "fk_outgoingmail")
    @JoinColumn(name = "OutgoingMailUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OutgoingMailBean outgoingMailBean;

    public OutgoingFileBean() {
    }

    public OutgoingFileBean(Long uid) {
        this.uid = uid;
    }

    public OutgoingFileBean(Long uid, String fileName, byte[] blobField) {
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

    public OutgoingMailBean getOutgoingMailBean() {
        return outgoingMailBean;
    }

    public void setOutgoingMailBean(OutgoingMailBean outgoingMailBean) {
        this.outgoingMailBean = outgoingMailBean;
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
        if (!(object instanceof OutgoingFileBean)) {
            return false;
        }
        OutgoingFileBean other = (OutgoingFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.OutgoingFileBean[uid=" + uid + "]";
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}