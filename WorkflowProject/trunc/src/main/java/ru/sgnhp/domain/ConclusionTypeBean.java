package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 77han
 */
@Entity
@Table(name = "conclusion_types", catalog = "workflowdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConclusionTypeBean.findAll", query = "SELECT c FROM ConclusionTypeBean c"),
    @NamedQuery(name = "ConclusionTypeBean.findByUid", query = "SELECT c FROM ConclusionTypeBean c WHERE c.uid = :uid"),
    @NamedQuery(name = "ConclusionTypeBean.findByName", query = "SELECT c FROM ConclusionTypeBean c WHERE c.name = :name")})
public class ConclusionTypeBean implements Serializable {
    private static final long serialVersionUID = 280620112L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "Name", length = 150)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conclusionTypeBean", fetch = FetchType.LAZY)
    private Collection<ConclusionBean> conclusionBeanCollection;

    public ConclusionTypeBean() {
    }

    public ConclusionTypeBean(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ConclusionBean> getConclusionBeanCollection() {
        return conclusionBeanCollection;
    }

    public void setConclusionBeanCollection(Collection<ConclusionBean> conclusionBeanCollection) {
        this.conclusionBeanCollection = conclusionBeanCollection;
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
        if (!(object instanceof ConclusionTypeBean)) {
            return false;
        }
        ConclusionTypeBean other = (ConclusionTypeBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.ConclusionTypeBean[ uid=" + uid + " ]";
    }
    
}
