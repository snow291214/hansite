package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author 77han
 */
@Entity
@Table(name = "negotiations", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "NegotiationBean.findAll", query = "SELECT n FROM NegotiationBean n"),
    @NamedQuery(name = "NegotiationBean.findByUid", query = "SELECT n FROM NegotiationBean n WHERE n.uid = :uid"),
    @NamedQuery(name = "NegotiationBean.findNegotiationsByUser", query = "SELECT n FROM NegotiationBean n WHERE n.workflowUserBean = :workflowUserBean"),
    @NamedQuery(name = "NegotiationBean.findByStartDate", query = "SELECT n FROM NegotiationBean n WHERE n.startDate = :startDate"),
    @NamedQuery(name = "NegotiationBean.findByDueDate", query = "SELECT n FROM NegotiationBean n WHERE n.dueDate = :dueDate"),
    @NamedQuery(name = "NegotiationBean.findByFinishDate", query = "SELECT n FROM NegotiationBean n WHERE n.finishDate = :finishDate")})
public class NegotiationBean implements Serializable {
    @Column(name =     "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name =     "DueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name =     "FinishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @Lob
    @Column(name = "Description", length = 2147483647)
    private String description;
    @ForeignKey(name = "fk_negotiations_users")
    @JoinColumn(name = "UserUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false)
    private WorkflowUserBean workflowUserBean;
    private static final long serialVersionUID = 280620113L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "negotiationBean", fetch = FetchType.LAZY)
    private Collection<ConclusionBean> conclusionBeanCollection;
    
    @OneToMany(mappedBy = "negotiationBean", fetch = FetchType.LAZY)
    private Collection<NegotiationFileBean> negotiationFileBeanCollection;
    
    @ForeignKey(name = "fk_negotiations_negotiation_types")
    @JoinColumn(name = "NegotiationTypeUid", referencedColumnName = "Uid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NegotiationTypeBean negotiationTypeBean;
    
    public NegotiationBean() {
    }

    public NegotiationBean(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @XmlTransient
    public Collection<ConclusionBean> getConclusionBeanCollection() {
        return conclusionBeanCollection;
    }

    public void setConclusionBeanCollection(Collection<ConclusionBean> conclusionBeanCollection) {
        this.conclusionBeanCollection = conclusionBeanCollection;
    }

    @XmlTransient
    public Collection<NegotiationFileBean> getNegotiationFileBeanCollection() {
        return negotiationFileBeanCollection;
    }

    public void setNegotiationFileBeanCollection(Collection<NegotiationFileBean> negotiationFileBeanCollection) {
        this.negotiationFileBeanCollection = negotiationFileBeanCollection;
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
        if (!(object instanceof NegotiationBean)) {
            return false;
        }
        NegotiationBean other = (NegotiationBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.NegotiationBean[ uid=" + uid + " ]";
    }

    /**
     * @return the negotiationTypeBean
     */
    public NegotiationTypeBean getNegotiationTypeBean() {
        return negotiationTypeBean;
    }

    /**
     * @param negotiationTypeBean the negotiationTypeBean to set
     */
    public void setNegotiationTypeBean(NegotiationTypeBean negotiationTypeBean) {
        this.negotiationTypeBean = negotiationTypeBean;
    }

    /**
     * @return the workflowUserBean
     */
    public WorkflowUserBean getWorkflowUserBean() {
        return workflowUserBean;
    }

    /**
     * @param workflowUserBean the workflowUserBean to set
     */
    public void setWorkflowUserBean(WorkflowUserBean workflowUserBean) {
        this.workflowUserBean = workflowUserBean;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
