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
@Table(name = "negotiation_types", catalog = "workflowdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NegotiationTypeBean.findAll", query = "SELECT n FROM NegotiationTypeBean n"),
    @NamedQuery(name = "NegotiationTypeBean.findByUid", query = "SELECT n FROM NegotiationTypeBean n WHERE n.uid = :uid"),
    @NamedQuery(name = "NegotiationTypeBean.findByName", query = "SELECT n FROM NegotiationTypeBean n WHERE n.name = :name")})
public class NegotiationTypeBean implements Serializable {
    private static final long serialVersionUID = 13072011L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "Name", length = 20)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "negotiationTypeBean", fetch = FetchType.LAZY)
    private Collection<NegotiationBean> negotiationBeanCollection;

    public NegotiationTypeBean() {
    }

    public NegotiationTypeBean(Long uid) {
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
    public Collection<NegotiationBean> getNegotiationBeanCollection() {
        return negotiationBeanCollection;
    }

    public void setNegotiationBeanCollection(Collection<NegotiationBean> negotiationBeanCollection) {
        this.negotiationBeanCollection = negotiationBeanCollection;
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
        if (!(object instanceof NegotiationTypeBean)) {
            return false;
        }
        NegotiationTypeBean other = (NegotiationTypeBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.NegotiationTypeBean[ uid=" + uid + " ]";
    }
    
}
