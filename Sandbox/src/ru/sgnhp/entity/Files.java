/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author alexey
 */
@Entity
@Table(name = "files", catalog = "workflowdb", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"Uid"})})
@NamedQueries({@NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f"), @NamedQuery(name = "Files.findByUid", query = "SELECT f FROM Files f WHERE f.uid = :uid"), @NamedQuery(name = "Files.findByFileName", query = "SELECT f FROM Files f WHERE f.fileName = :fileName")})
public class Files implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Column(name = "FileName", length = 100)
    private String fileName;
    @Lob
    @Column(name = "Blob")
    private byte[] blob;
    @JoinColumn(name = "TaskUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tasks taskUid;

    public Files() {
    }

    public Files(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
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
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.entity.Files[uid=" + uid + "]";
    }

}
