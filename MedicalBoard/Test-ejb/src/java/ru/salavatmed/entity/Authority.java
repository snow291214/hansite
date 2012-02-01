package ru.salavatmed.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Khudyakov Alexey
 * Skype: khudyakov.alexey
 * Email: khudyakov.alexey@gmail.com
 * 
 */
@Entity
@Table(name = "authorities", catalog = "dashboard", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"),
    @NamedQuery(name = "Authority.findByUid", query = "SELECT a FROM Authority a WHERE a.uid = :uid"),
    @NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authority = :authority")})
public class Authority implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Uid", nullable = false)
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MedialogCode", nullable = false)
    private int medialogCode;
    @Size(max = 255)
    @Column(name = "OKVEDName", length = 255)
    private String oKVEDName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FullName", nullable = false, length = 255)
    private String fullName;
    @Size(max = 100)
    @Column(name = "FormOfProperty", length = 100)
    private String formOfProperty;
    @Size(max = 50)
    @Column(name = "OKVED", length = 50)
    private String okved;
    @Size(max = 200)
    @Column(name = "Manager", length = 200)
    private String manager;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Authority", nullable = false, length = 50)
    private String authority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authority")
    private Collection<User> userCollection;

    public Authority() {
    }

    public Authority(Integer uid) {
        this.uid = uid;
    }

    public Authority(Integer uid, String authority) {
        this.uid = uid;
        this.authority = authority;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.salavatmed.entity.Authority[ uid=" + uid + " ]";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFormOfProperty() {
        return formOfProperty;
    }

    public void setFormOfProperty(String formOfProperty) {
        this.formOfProperty = formOfProperty;
    }

    public String getOkved() {
        return okved;
    }

    public void setOkved(String okved) {
        this.okved = okved;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOKVEDName() {
        return oKVEDName;
    }

    public void setOKVEDName(String oKVEDName) {
        this.oKVEDName = oKVEDName;
    }

    public int getMedialogCode() {
        return medialogCode;
    }

    public void setMedialogCode(int medialogCode) {
        this.medialogCode = medialogCode;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }
    
}
