package net.skytelecom.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */

@Entity
@Table(name = "authorities", catalog = "skyteldb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username", "authority"})})
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a"),
    @NamedQuery(name = "Authority.findByUid", query = "SELECT a FROM Authority a WHERE a.uid = :uid"),
    @NamedQuery(name = "Authority.findByUsername", query = "SELECT a FROM Authority a WHERE a.username = :username"),
    @NamedQuery(name = "Authority.findByAuthority", query = "SELECT a FROM Authority a WHERE a.authority = :authority")})
public class Authority implements Serializable {
    private static final long serialVersionUID = 7358561790082669321L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic(optional = false)
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    public Authority() {
    }

    public Authority(Long uid) {
        this.uid = uid;
    }

    public Authority(Long uid, String username, String authority) {
        this.uid = uid;
        this.username = username;
        this.authority = authority;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "net.skytelecom.entity.Authority[uid=" + uid + "]";
    }

}
