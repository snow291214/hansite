package ru.sgnhp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "state", catalog = "workflowdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "StateBean.findAll", query = "SELECT s FROM StateBean s"),
    @NamedQuery(name = "StateBean.findByStateUid", query = "SELECT s FROM StateBean s WHERE s.stateUid = :stateUid"),
    @NamedQuery(name = "StateBean.findByStateDescription", query = "SELECT s FROM StateBean s WHERE s.stateDescription = :stateDescription")})
    
public class StateBean implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Basic(optional = false)
    @Column(name = "StateUid", nullable = false)
    private Long stateUid;
    @Basic(optional = false)
    @Column(name = "StateDescription", nullable = false, length = 10)
    private String stateDescription;

    @OneToMany(mappedBy = "stateBean")
    private Set<WorkflowBean> workflowsSet = new HashSet<WorkflowBean>();

    public StateBean() {
    }

    public StateBean(Long stateUid) {
        this.stateUid = stateUid;
    }

    public StateBean(Long stateUid, String stateDescription) {
        this.stateUid = stateUid;
        this.stateDescription = stateDescription;
    }

    public Long getStateUid() {
        return stateUid;
    }

    public void setStateUid(Long stateUid) {
        this.stateUid = stateUid;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateUid != null ? stateUid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StateBean)) {
            return false;
        }
        StateBean other = (StateBean) object;
        if ((this.stateUid == null && other.stateUid != null) || (this.stateUid != null && !this.stateUid.equals(other.stateUid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.StateBean[stateUid=" + stateUid + "]";
    }

    public Set<WorkflowBean> getWorkflowsSet() {
        return workflowsSet;
    }

    public void setWorkflowsSet(Set<WorkflowBean> workflowsSet) {
        this.workflowsSet = workflowsSet;
    }

}
