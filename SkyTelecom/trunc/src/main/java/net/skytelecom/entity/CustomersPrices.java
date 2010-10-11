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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 15.08.2010
 */
@Entity
@Table(name = "customers_prices", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "CustomersPrices.findAll", query = "SELECT c FROM CustomersPrices c"),
    @NamedQuery(name = "CustomersPrices.findByUid", query = "SELECT c FROM CustomersPrices c WHERE c.uid = :uid")
//    @NamedQuery(name = "CustomersPrices.findByCustomerUid", query = "SELECT c FROM CustomersPrices c WHERE c.customerUid = :customerUid"),
//    @NamedQuery(name = "CustomersPrices.findByPriceTypeUid", query = "SELECT c FROM CustomersPrices c WHERE c.priceTypeUid = :priceTypeUid")
})
public class CustomersPrices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;

    @ForeignKey(name = "fk_prices_customers_customers")
    @JoinColumn(name = "CustomerUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ForeignKey(name = "fk_prices_customers_priceTypes")
    @JoinColumn(name = "PriceTypeUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private PriceType priceType;


    @OneToMany(mappedBy = "customersPrices", fetch = FetchType.LAZY)
    @OrderBy("destination")
    private Collection<Price> prices;

    public CustomersPrices() {
    }

    public CustomersPrices(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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
        if (!(object instanceof CustomersPrices)) {
            return false;
        }
        CustomersPrices other = (CustomersPrices) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.CustomersPrices[uid=" + uid + "]";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public Collection<Price> getPrices() {
        return prices;
    }

    public void setPrices(Collection<Price> prices) {
        this.prices = prices;
    }
}
