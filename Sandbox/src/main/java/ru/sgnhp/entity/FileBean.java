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
@Table(name = "files", catalog = "workflowdb", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({
    @NamedQuery(name = "FileBean.findAll", query = "SELECT f FROM FileBean f"),
    @NamedQuery(name = "FileBean.findByUid", query = "SELECT f FROM FileBean f WHERE f.uid = :uid"),
    @NamedQuery(name = "FileBean.findByTask", query = "SELECT f FROM FileBean f WHERE f.tasks = :tasks"),
    @NamedQuery(name = "FileBean.findByFileName", query = "SELECT f FROM FileBean f WHERE f.fileName = :fileName")
})
public class FileBean implements Serializable {
    private static final long serialVersionUID = 5L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "FileName", length = 100, nullable=false)
    private String fileName;
    @Column(name = "FilePath", length = 255, nullable=false)
    private String filePath;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "BlobField", columnDefinition = "LONGBLOB")
    private byte[] blobField;
    @ForeignKey(name="fk_tasks")
    @JoinColumn(name = "TaskUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskBean tasks;

    public FileBean() {
    }

    public FileBean(Long uid) {
        this.uid = uid;
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

    public TaskBean getTaskUid() {
        return tasks;
    }

    public void setTaskUid(TaskBean taskUid) {
        this.tasks = taskUid;
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
        if (!(object instanceof FileBean)) {
            return false;
        }
        FileBean other = (FileBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.Files[uid=" + uid + "]";
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}