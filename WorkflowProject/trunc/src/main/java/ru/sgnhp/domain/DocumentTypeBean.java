/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
@Entity
@Table(name = "document_types", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "DocumentTypeBean.findAll", query = "SELECT d FROM DocumentTypeBean d"),
    @NamedQuery(name = "DocumentTypeBean.findByUid", query = "SELECT d FROM DocumentTypeBean d WHERE d.uid = :uid"),
    @NamedQuery(name = "DocumentTypeBean.findByDescription", query = "SELECT d FROM DocumentTypeBean d WHERE d.description = :description")})
public class DocumentTypeBean implements Serializable {
    private static final long serialVersionUID = 12L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false, length = 50)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentTypeBean", fetch = FetchType.LAZY)
    private Set<DocumentBean> documentBeanSet;

    public DocumentTypeBean() {
    }

    public DocumentTypeBean(Long uid) {
        this.uid = uid;
    }

    public DocumentTypeBean(Long uid, String description) {
        this.uid = uid;
        this.description = description;
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

    public Set<DocumentBean> getDocumentBeanSet() {
        return documentBeanSet;
    }

    public void setDocumentBeanSet(Set<DocumentBean> documentBeanSet) {
        this.documentBeanSet = documentBeanSet;
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
        if (!(object instanceof DocumentTypeBean)) {
            return false;
        }
        DocumentTypeBean other = (DocumentTypeBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.DocumentTypeBean[uid=" + uid + "]";
    }

}
