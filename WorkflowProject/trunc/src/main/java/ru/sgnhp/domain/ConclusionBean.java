package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author 77han
 */
@Entity
@Table(name = "conclusions", catalog = "workflowdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConclusionBean.findAll", query = "SELECT c FROM ConclusionBean c"),
    @NamedQuery(name = "ConclusionBean.findByUid", query = "SELECT c FROM ConclusionBean c WHERE c.uid = :uid"),
    @NamedQuery(name = "ConclusionBean.findByUserUid", query = "SELECT c FROM ConclusionBean c WHERE c.userUid = :userUid"),
    @NamedQuery(name = "ConclusionBean.findByDateOfConclusion", query = "SELECT c FROM ConclusionBean c WHERE c.dateOfConclusion = :dateOfConclusion")})
public class ConclusionBean implements Serializable {
    private static final long serialVersionUID = 280620111L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "UserUid", nullable = false)
    private Long userUid;
    @Basic(optional = false)
    @Column(name = "DateOfConclusion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfConclusion;
    @Lob
    @Column(name = "Description", length = 65535)
    private String description;
    @ForeignKey(name = "fk_conclusions_negotiations")
    @JoinColumn(name = "NegotiationUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NegotiationBean negotiationBean;
    @ForeignKey(name = "fk_conclusions_conclusion_types")
    @JoinColumn(name = "ConclusionTypeUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ConclusionTypeBean conclusionTypeBean;

    public ConclusionBean() {
    }

    public ConclusionBean(Long uid) {
        this.uid = uid;
    }

    public ConclusionBean(Long uid, Long userUid, Date dateOfConclusion) {
        this.uid = uid;
        this.userUid = userUid;
        this.dateOfConclusion = dateOfConclusion;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUserUid() {
        return userUid;
    }

    public void setUserUid(Long userUid) {
        this.userUid = userUid;
    }

    public Date getDateOfConclusion() {
        return dateOfConclusion;
    }

    public void setDateOfConclusion(Date dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof ConclusionBean)) {
            return false;
        }
        ConclusionBean other = (ConclusionBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.ConclusionBean[ uid=" + uid + " ]";
    }

    /**
     * @return the negotiationBean
     */
    public NegotiationBean getNegotiationBean() {
        return negotiationBean;
    }

    /**
     * @param negotiationBean the negotiationBean to set
     */
    public void setNegotiationBean(NegotiationBean negotiationBean) {
        this.negotiationBean = negotiationBean;
    }

    /**
     * @return the conclusionTypeBean
     */
    public ConclusionTypeBean getConclusionTypeBean() {
        return conclusionTypeBean;
    }

    /**
     * @param conclusionTypeBean the conclusionTypeBean to set
     */
    public void setConclusionTypeBean(ConclusionTypeBean conclusionTypeBean) {
        this.conclusionTypeBean = conclusionTypeBean;
    }
    
}
