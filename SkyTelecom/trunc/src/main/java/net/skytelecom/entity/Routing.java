package net.skytelecom.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 18.07.2010
 */

@Entity
@Table(name = "routing", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Routing.findAll", query = "SELECT r FROM Routing r"),
    @NamedQuery(name = "Routing.findByUid", query = "SELECT r FROM Routing r WHERE r.uid = :uid"),
    @NamedQuery(name = "Routing.findByName", query = "SELECT r FROM Routing r WHERE r.name = :name")})
public class Routing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Column(name = "Name", length = 255)
    private String name;

    public Routing() {
    }

    public Routing(Long uid) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Routing)) {
            return false;
        }
        Routing other = (Routing) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.Routing[uid=" + uid + "]";
    }

}
