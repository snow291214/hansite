package net.skytelecom.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 09.08.2010
 */
@Entity
@Table(name = "price_types", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "PriceType.findAll", query = "SELECT p FROM PriceType p"),
    @NamedQuery(name = "PriceType.findByUid", query = "SELECT p FROM PriceType p WHERE p.uid = :uid"),
    @NamedQuery(name = "PriceType.findByName", query = "SELECT p FROM PriceType p WHERE p.name = :name")})
public class PriceType implements Serializable {

    private static final long serialVersionUID = -6899308407656688764L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false)
    private Long uid;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Column(name = "ShortName", nullable = false, length = 5)
    private String shortName;

    @OneToMany(mappedBy = "priceType", fetch = FetchType.LAZY)
    private Collection<CustomersPrices> customersPrices;

//    @OneToMany(mappedBy = "priceType", fetch = FetchType.LAZY)
//    private Collection<Price> prices;
    public PriceType() {
    }

    public PriceType(Long uid) {
        this.uid = uid;
    }

    public PriceType(Long uid, String name) {
        this.uid = uid;
        this.name = name;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceType)) {
            return false;
        }
        PriceType other = (PriceType) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.PriceType[uid=" + uid + "]";
    }

    public Collection<CustomersPrices> getCustomersPrices() {
        return customersPrices;
    }

    public void setCustomersPrices(Collection<CustomersPrices> customersPrices) {
        this.customersPrices = customersPrices;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
//    public Collection<Price> getPrices() {
//        return prices;
//    }
//
//    public void setPrices(Collection<Price> prices) {
//        this.prices = prices;
//    }
}
