package net.skytelecom.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 28.06.2010
 */

@Entity
@Table(name = "customers", catalog = "skyteldb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByUid", query = "SELECT c FROM Customer c WHERE c.uid = :uid"),
    @NamedQuery(name = "Customer.findByCustomerName", query = "SELECT c FROM Customer c WHERE c.customerName = :customerName"),
    @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "Customer.findByCurrency", query = "SELECT c FROM Customer c WHERE c.currency = :currency"),
    @NamedQuery(name = "Customer.findByContactPerson", query = "SELECT c FROM Customer c WHERE c.contactPerson = :contactPerson"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByHiddenEmail", query = "SELECT c FROM Customer c WHERE c.hiddenEmail = :hiddenEmail"),
    @NamedQuery(name = "Customer.findByEmailSubject", query = "SELECT c FROM Customer c WHERE c.emailSubject = :emailSubject"),
    @NamedQuery(name = "Customer.findByBlockedDestinations", query = "SELECT c FROM Customer c WHERE c.blockedDestinations = :blockedDestinations"),
    @NamedQuery(name = "Customer.findByLatestReport", query = "SELECT c FROM Customer c WHERE c.latestReport = :latestReport"),
    @NamedQuery(name = "Customer.findByUser", query = "SELECT c FROM Customer c WHERE c.user = :user order by c.customerName"),
    @NamedQuery(name = "Customer.findByUserAndWithPriceList", query = "SELECT c FROM Customer c WHERE c.user = :user and c.customersPrices.size > 0 order by c.customerName"),
    @NamedQuery(name = "Customer.findBySender", query = "SELECT c FROM Customer c WHERE c.sender = :sender")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 7349895648469106755L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Uid", nullable = false, columnDefinition = "INTEGER(11)")
    private Long uid;
    @Basic(optional = false)
    @Column(name = "CustomerName", nullable = false, length = 50)
    private String customerName;
    @Column(name = "CustomerId", length = 50)
    private String customerId;
    @Column(name = "Currency", length = 5)
    private String currency;
    @Column(name = "ContactPerson", length = 200)
    private String contactPerson;
    @Column(name = "Email", length = 200)
    private String email;
    @Column(name = "HiddenEmail", length = 200)
    private String hiddenEmail;
    @Column(name = "EmailSubject", length = 200)
    private String emailSubject;
    @Column(name = "BlockedDestinations", length = 255)
    private String blockedDestinations;
    @Column(name = "LatestReport")
    @Temporal(TemporalType.TIMESTAMP)
    private Date latestReport;
    @Column(name = "Sender", length = 50)
    private String sender;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Collection<CustomersPrices> customersPrices;

    @ForeignKey(name = "fk_customers_users")
    @JoinColumn(name = "UserUid", referencedColumnName = "Uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

   
    public Customer() {
    }

    public Customer(Long uid) {
        this.uid = uid;
    }

    public Customer(Long uid, String customerName) {
        this.uid = uid;
        this.customerName = customerName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHiddenEmail() {
        return hiddenEmail;
    }

    public void setHiddenEmail(String hiddenEmail) {
        this.hiddenEmail = hiddenEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getBlockedDestinations() {
        return blockedDestinations;
    }

    public void setBlockedDestinations(String blockedDestinations) {
        this.blockedDestinations = blockedDestinations;
    }

    public Date getLatestReport() {
        return latestReport;
    }

    public void setLatestReport(Date latestReport) {
        this.latestReport = latestReport;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.skytelecom.entity.Company[uid=" + uid + "]";
    }


    /**
     * @return the customersPrices
     */
    public Collection<CustomersPrices> getCustomersPrices() {
        return customersPrices;
    }

    /**
     * @param customersPrices the customersPrices to set
     */
    public void setCustomersPrices(Collection<CustomersPrices> customersPrices) {
        this.customersPrices = customersPrices;
    }
}
