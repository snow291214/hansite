package net.skytelecom.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */

@Entity
@Table(name = "persistent_logins", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersistentLogin.findAll", query = "SELECT p FROM PersistentLogin p"),
    @NamedQuery(name = "PersistentLogin.findByUsername", query = "SELECT p FROM PersistentLogin p WHERE p.username = :username"),
    @NamedQuery(name = "PersistentLogin.findBySeries", query = "SELECT p FROM PersistentLogin p WHERE p.series = :series"),
    @NamedQuery(name = "PersistentLogin.findByToken", query = "SELECT p FROM PersistentLogin p WHERE p.token = :token"),
    @NamedQuery(name = "PersistentLogin.findByLastUsed", query = "SELECT p FROM PersistentLogin p WHERE p.lastUsed = :lastUsed")})
public class PersistentLogin implements Serializable {
    private static final long serialVersionUID = 7171920406175086787L;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 64)
    private String username;
    @Id
    @Basic(optional = false)
    @Column(name = "series", nullable = false, length = 64)
    private String series;
    @Basic(optional = false)
    @Column(name = "token", nullable = false, length = 64)
    private String token;
    @Basic(optional = false)
    @Column(name = "last_used", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public PersistentLogin() {
    }

    public PersistentLogin(String series) {
        this.series = series;
    }

    public PersistentLogin(String series, String username, String token, Date lastUsed) {
        this.series = series;
        this.username = username;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (series != null ? series.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersistentLogin)) {
            return false;
        }
        PersistentLogin other = (PersistentLogin) object;
        if ((this.series == null && other.series != null) || (this.series != null && !this.series.equals(other.series))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.PersistentLogin[series=" + series + "]";
    }

}
