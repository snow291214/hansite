package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "groups", catalog = "workflowdb", schema = "")
@NamedQueries({@NamedQuery(name = "UserGroupBean.findAll", query = "SELECT u FROM UserGroupBean u"), @NamedQuery(name = "UserGroupBean.findByUid", query = "SELECT u FROM UserGroupBean u WHERE u.uid = :uid"), @NamedQuery(name = "UserGroupBean.findByDescription", query = "SELECT u FROM UserGroupBean u WHERE u.description = :description"), @NamedQuery(name = "UserGroupBean.findByName", query = "SELECT u FROM UserGroupBean u WHERE u.name = :name")})
public class UserGroupBean implements Serializable {
    private static final long serialVersionUID = 8L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, length=4)
    private Short uid;
    @Column(name = "Description", length = 150)
    private String description;
    @Column(name = "Name", length = 20)
    private String name;

    @OneToMany(mappedBy = "userGroupBean")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<WorkflowUserBean> usersSet = new HashSet<WorkflowUserBean>();

    public UserGroupBean() {
    }

    public UserGroupBean(Short uid) {
        this.uid = uid;
    }

    public Short getUid() {
        return uid;
    }

    public void setUid(Short uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof UserGroupBean)) {
            return false;
        }
        UserGroupBean other = (UserGroupBean) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.UserGroupBean[uid=" + uid + "]";
    }

    public Set<WorkflowUserBean> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<WorkflowUserBean> usersSet) {
        this.usersSet = usersSet;
    }
}