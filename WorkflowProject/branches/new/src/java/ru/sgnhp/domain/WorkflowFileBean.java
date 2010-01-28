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
@Table(name = "workflowfiles", catalog = "workflowdb", schema = "")
@NamedQueries({@NamedQuery(name = "WorkflowFileBean.findAll", query = "SELECT w FROM WorkflowFileBean w")
})
public class WorkflowFileBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false,fetch=FetchType.LAZY)
    @Lob
    @Column(name = "BlobField", nullable = false)
    private byte[] blobField;
    @Basic(optional = false)
    @Column(name = "FileName", nullable = false, length = 100)
    private String fileName;

    @ForeignKey(name = "fk_workflowfiles_workflow")
    @JoinColumn(name = "WorkflowUid", referencedColumnName = "Uid", nullable = false, columnDefinition = "INTEGER(11) UNSIGNED")
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkflowBean workflowBean;

    public WorkflowFileBean() {
    }

    public WorkflowFileBean(Long uid) {
        this.uid = uid;
    }

    public WorkflowFileBean(Long uid, byte[] blobField, String fileName, int workflowUid) {
        this.uid = uid;
        this.blobField = blobField;
        this.fileName = fileName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public byte[] getBlobField() {
        return blobField;
    }

    public void setBlobField(byte[] blobField) {
        this.blobField = blobField;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        if (!(object instanceof WorkflowFileBean)) {
            return false;
        }
        WorkflowFileBean other = (WorkflowFileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.WorkflowFileBean[uid=" + uid + "]";
    }

    public WorkflowBean getWorkflowBean() {
        return workflowBean;
    }

    public void setWorkflowBean(WorkflowBean workflowBean) {
        this.workflowBean = workflowBean;
    }
}
