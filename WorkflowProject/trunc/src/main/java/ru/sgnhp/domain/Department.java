package ru.sgnhp.domain;

import java.io.Serializable;
import java.text.Collator;
import java.util.Collection;
import java.util.Locale;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * Khudyakov Alexey 
 * Skype: khudyakov.alexey 
 * Email: khudyakov.alexey@gmail.com
 *
 */
@Entity
@Table(name = "departments", catalog = "workflowdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByUid", query = "SELECT d FROM Department d WHERE d.uid = :uid"),
    @NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d FROM Department d WHERE d.departmentName = :departmentName")})
public class Department implements Serializable, Comparable<Department> {

    private static final long serialVersionUID = 20L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Column(name = "DepartmentName", length = 255)
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("lastName")
//    @JoinTable
//    @FilterJoinTable(name="workflowUserBean", condition="enabled = true")
    private Collection<WorkflowUserBean> workflowUserBeanCollection;
    @Basic(optional = false)
    @Column(name = "ParentUid", nullable = false)
    private int parentUid;

    public Department() {
    }

    public Department(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.sgnhp.domain.Department[ uid=" + uid + " ]";
    }

    @XmlTransient
    public Collection<WorkflowUserBean> getWorkflowUserBeanCollection() {
        return workflowUserBeanCollection;
    }

    public void setWorkflowUserBeanCollection(Collection<WorkflowUserBean> workflowUserBeanCollection) {
        this.workflowUserBeanCollection = workflowUserBeanCollection;
    }

    @Override
    public int compareTo(Department department) {
        Collator collator = Collator.getInstance(Locale.getDefault());
        return collator.compare(this.getDepartmentName(), department.getDepartmentName());
    }

    public int getParentUid() {
        return parentUid;
    }

    public void setParentUid(int parentUid) {
        this.parentUid = parentUid;
    }
}
